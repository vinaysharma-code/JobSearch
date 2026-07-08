package com.jobSearch.service;

import com.jobSearch.dto.request.UpdateApplicantProfileRequest;
import com.jobSearch.dto.response.ApplicantResponse;
import com.jobSearch.dto.response.ApplicationResponse;
import com.jobSearch.entity.Applicant;
import com.jobSearch.entity.User;
import com.jobSearch.exception.ResourceNotFoundException;
import com.jobSearch.repository.ApplicantRepo;
import com.jobSearch.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ApplicantService {
    private final UserRepo userRepo;
    private  final ApplicantRepo applicantRepo;
    public ApplicantService(UserRepo userRepo, ApplicantRepo applicantRepo) {
        this.userRepo = userRepo;
        this.applicantRepo = applicantRepo;
    }

    public ApplicantResponse getApplicant() {
        User user = getCurrentUser();

        Applicant applicant =
                applicantRepo.findByUserId(user.getId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Applicant not found"));

        return mapToApplicant(applicant);
    }
    public ApplicantResponse updateApplicant(
            UpdateApplicantProfileRequest request) {

        User user = getCurrentUser();

        Applicant applicant =
                applicantRepo.findByUserId(user.getId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Applicant not found"));

        if (request.getCity() != null) {
            applicant.setCity(request.getCity());
        }

        if (request.getPhone() != null) {
            applicant.setPhone(request.getPhone());
        }

        if (request.getExperience() != null) {
            applicant.setExperience(request.getExperience());
        }

        if (request.getEducation() != null) {
            applicant.setEducation(request.getEducation());
        }

        if (request.getSkills() != null) {
            applicant.setSkills(request.getSkills());
        }

        if (request.getLinkedinUrl() != null) {
            applicant.setLinkedinUrl(request.getLinkedinUrl());
        }

        if (request.getGithubUrl() != null) {
            applicant.setGithubUrl(request.getGithubUrl());
        }

        if (request.getResumeUrl() != null) {
            applicant.setResumeUrl(request.getResumeUrl());
        }

        applicant.setUpdatedAt(LocalDateTime.now());

        Applicant saved = applicantRepo.save(applicant);

        return mapToApplicant(saved);
    }
    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();
        return userRepo.findByEmail(user).orElseThrow(()->new ResourceNotFoundException("User  not found"));

    }
    public ApplicantResponse mapToApplicant(Applicant applicant) {
        ApplicantResponse response = new ApplicantResponse();
        response.setCity(applicant.getCity());
        response.setEducation(applicant.getEducation());
        response.setExperience(applicant.getExperience());
        response.setPhone(applicant.getPhone());
        response.setSkills(applicant.getSkills());
        response.setLinkedinUrl(applicant.getLinkedinUrl());
        response.setFullName(applicant.getFullName());
        response.setUpdatedAt(applicant.getUpdatedAt());
        response.setGithubUrl(applicant.getGithubUrl());
        response.setResumeUrl(applicant.getResumeUrl());

        return response;
    }
}
