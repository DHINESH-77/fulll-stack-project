package com.complaint.controller;

import com.complaint.dto.Dtos.*;
import com.complaint.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/complaints")
@RequiredArgsConstructor
public class ComplaintController {

    private final ComplaintService complaintService;

    @PostMapping
    public ResponseEntity<ComplaintResponse> submit(@RequestBody ComplaintRequest request) {
        return ResponseEntity.ok(complaintService.submit(request));
    }

    @GetMapping
    public ResponseEntity<List<ComplaintResponse>> getMyComplaints() {
        return ResponseEntity.ok(complaintService.getMyComplaints());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComplaintResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(complaintService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        complaintService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
