package com.complaint.service;

import com.complaint.dto.Dtos.*;
import com.complaint.model.Complaint;
import com.complaint.model.User;
import com.complaint.repository.ComplaintRepository;
import com.complaint.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final UserRepository userRepository;

    private User currentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username).orElseThrow();
    }

    private ComplaintResponse toResponse(Complaint c) {
        ComplaintResponse res = new ComplaintResponse();
        res.setId(c.getId());
        res.setTitle(c.getTitle());
        res.setDescription(c.getDescription());
        res.setCategory(c.getCategory());
        res.setStatus(c.getStatus());
        res.setAdminResponse(c.getAdminResponse());
        res.setUsername(c.getUser().getUsername());
        res.setCreatedAt(c.getCreatedAt());
        res.setUpdatedAt(c.getUpdatedAt());
        return res;
    }

    public ComplaintResponse submit(ComplaintRequest request) {
        Complaint complaint = new Complaint();
        complaint.setTitle(request.getTitle());
        complaint.setDescription(request.getDescription());
        complaint.setCategory(request.getCategory());
        complaint.setUser(currentUser());
        return toResponse(complaintRepository.save(complaint));
    }

    public List<ComplaintResponse> getMyComplaints() {
        return complaintRepository.findByUserId(currentUser().getId())
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public ComplaintResponse getById(Long id) {
        Complaint c = complaintRepository.findById(id).orElseThrow(() -> new RuntimeException("Complaint not found"));
        User user = currentUser();
        if (!c.getUser().getId().equals(user.getId()) && user.getRole() != User.Role.ADMIN)
            throw new RuntimeException("Access denied");
        return toResponse(c);
    }

    public void delete(Long id) {
        Complaint c = complaintRepository.findById(id).orElseThrow(() -> new RuntimeException("Complaint not found"));
        if (!c.getUser().getId().equals(currentUser().getId()))
            throw new RuntimeException("Access denied");
        complaintRepository.delete(c);
    }

    // Admin operations
    public List<ComplaintResponse> getAllComplaints() {
        return complaintRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    public ComplaintResponse updateStatus(Long id, StatusUpdateRequest request) {
        Complaint c = complaintRepository.findById(id).orElseThrow(() -> new RuntimeException("Complaint not found"));
        c.setStatus(request.getStatus());
        c.setAdminResponse(request.getAdminResponse());
        c.setUpdatedAt(LocalDateTime.now());
        return toResponse(complaintRepository.save(c));
    }
}
