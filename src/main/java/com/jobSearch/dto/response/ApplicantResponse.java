package com.jobSearch.dto.response;

import lombok.Data;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class ApplicantResponse {
    private ObjectId userId;
    private String  fullName;
    private String phone;
    private List<String> skills;;
    private String education;
    private int experience;
    private String city;
    private String linkedinUrl ;
    private String githubUrl;
    private String resumeUrl;
    private LocalDateTime updatedAt;
}
