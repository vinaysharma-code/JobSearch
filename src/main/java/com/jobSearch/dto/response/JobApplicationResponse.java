package com.jobSearch.dto.response;

import com.jobSearch.enums.ApplicationStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class JobApplicationResponse {


        private String applicantName;
        private String phone;
        private List<String> skills;
        private int experience;
        private String resumeUrl;

        private ApplicationStatus status;
        private LocalDateTime appliedAt;


}
