package com.complaint.dto;

import com.complaint.model.Complaint.Status;
import lombok.Data;
import java.time.LocalDateTime;

public class Dtos {

    @Data
    public static class RegisterRequest {
        private String username;
        private String email;
        private String password;
    }

    @Data
    public static class LoginRequest {
        private String username;
        private String password;
    }

    @Data
    public static class AuthResponse {
        private String token;
        private String username;
        private String role;

        public AuthResponse(String token, String username, String role) {
            this.token = token;
            this.username = username;
            this.role = role;
        }
    }

    @Data
    public static class ComplaintRequest {
        private String title;
        private String description;
        private String category;
    }

    @Data
    public static class ComplaintResponse {
        private Long id;
        private String title;
        private String description;
        private String category;
        private Status status;
        private String adminResponse;
        private String username;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Data
    public static class StatusUpdateRequest {
        private Status status;
        private String adminResponse;
    }
}
