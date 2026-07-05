package com.jobSearch.controller;

import com.jobSearch.dto.request.RecruiterRequest;
import com.jobSearch.dto.request.UpdateRecruiterProfileRequest;
import com.jobSearch.dto.response.RecruiterResponse;
import com.jobSearch.service.RecruiterService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recruiter")
public class RecruiterController {

    private final RecruiterService recruiterService;

    public RecruiterController(RecruiterService recruiterService){
        this.recruiterService = recruiterService;
    }


    @GetMapping("/profile")
    public ResponseEntity<RecruiterResponse> getProfile() {
        return ResponseEntity.ok(recruiterService.getProfile());
    }
    @PostMapping("/profile")
    public ResponseEntity<RecruiterResponse> createProfile(@Valid @RequestBody RecruiterRequest recruiter) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(recruiterService.createRecruiter(recruiter));
    }

    @PatchMapping("/profile")
    public ResponseEntity<RecruiterResponse> updateProfile(
            @Valid @RequestBody UpdateRecruiterProfileRequest request) {

        return ResponseEntity.ok(recruiterService.updateProfile(request));
    }




}
