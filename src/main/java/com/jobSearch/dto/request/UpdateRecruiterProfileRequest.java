package com.jobSearch.dto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class UpdateRecruiterProfileRequest {

    @Size(max = 100, message = "Full name cannot exceed 100 characters")
    private String fullName;

    @Size(max = 100, message = "Company name cannot exceed 100 characters")
    private String companyName;

    @Size(max = 500, message = "Company description cannot exceed 500 characters")
    private String companyDescription;

    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Enter a valid 10-digit phone number"
    )

    private String phone;
}