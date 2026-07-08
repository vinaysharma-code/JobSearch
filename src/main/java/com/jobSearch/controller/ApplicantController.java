package com.jobSearch.controller;

import com.jobSearch.dto.request.UpdateApplicantProfileRequest;
import com.jobSearch.dto.response.ApplicantResponse;
import com.jobSearch.entity.Applicant;
import com.jobSearch.entity.User;
import com.jobSearch.service.ApplicantService;
import jakarta.validation.Valid;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/applicants")
public class ApplicantController {


    private final ApplicantService applicantService;
    public  ApplicantController(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }
    @GetMapping("/profile")
    public ResponseEntity<ApplicantResponse> getProfile() {
        return ResponseEntity.ok(
                applicantService.getApplicant());
    }

    @PutMapping("/profile")
    public ResponseEntity<ApplicantResponse> updateProfile(
           @Valid @RequestBody UpdateApplicantProfileRequest request) {

        return ResponseEntity.ok(
                applicantService.updateApplicant(request));
    }
}
