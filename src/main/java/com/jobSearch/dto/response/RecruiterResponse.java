package com.jobSearch.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RecruiterResponse {
    private String fullName;
    private String companyName;
    private String companyDescription;
    private String phone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String email;
}