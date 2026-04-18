package com.complaint.controller;

import com.complaint.dto.Dtos.*;
import com.complaint.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminController {

    private final ComplaintService complaintService;

    @GetMapping("/complaints")
    public ResponseEntity<List<ComplaintResponse>> getAllComplaints() {
        return ResponseEntity.ok(complaintService.getAllComplaints());
    }

    @PutMapping("/complaints/{id}/status")
    public ResponseEntity<ComplaintResponse> updateStatus(@PathVariable Long id,
                                                          @RequestBody StatusUpdateRequest request) {
        return ResponseEntity.ok(complaintService.updateStatus(id, request));
    }
}
