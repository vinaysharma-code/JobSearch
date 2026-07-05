package com.jobSearch.controller;

import com.jobSearch.dto.request.UpdateApplicantProfileRequest;
import com.jobSearch.entity.Applicant;
import com.jobSearch.service.ApplicantService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/applicants")
public class ApplicantController {

    @Autowired
    private ApplicantService applicantService;

    @GetMapping("/{applicantId}")
    private Applicant getApplicant(@PathVariable ObjectId applicantId) {

    }
    @PutMapping
    private ResponseEntity<?> updateApplicant(@RequestBody UpdateApplicantProfileRequest  updateApplicantProfileRequest) {

    }

}
