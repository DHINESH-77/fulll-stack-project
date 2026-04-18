package com.complaint.repository;

import com.complaint.model.Complaint;
import com.complaint.model.Complaint.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findByUserId(Long userId);
    List<Complaint> findByStatus(Status status);
}
