package com.jobSearch.dto.response;

import com.jobSearch.enums.ApplicationStatus;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ApplicationResponse {
    private String applicationId;
    private String jobTitle;
    private String companyName;
    private ApplicationStatus status;
    private LocalDateTime appliedAt;
}
