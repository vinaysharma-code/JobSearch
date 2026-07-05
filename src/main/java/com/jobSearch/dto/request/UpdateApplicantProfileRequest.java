package com.jobSearch.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
@Data
public class UpdateApplicantProfileRequest {
    @NotBlank
    @Size(max = 100)
    private String fullName;
    @NotBlank
    @Size(max = 100)
    private String phone;
    @NotBlank
    @Size(max = 100)
    private List<String> skills;
    @NotBlank
    @Size(max = 100)
    private String education;

    @Positive
    @Size(max = 100)
    private int experience;
    @NotBlank
    @Size(max = 100)
    private String city;
    @NotBlank
    private String address;
    @NotBlank
    private String linkedinUrl ;
    @NotBlank
    @Size(max = 100)
    private String githubUrl;
    @NotBlank
    @Size(max = 100)
    private String resumeUrl;
}
