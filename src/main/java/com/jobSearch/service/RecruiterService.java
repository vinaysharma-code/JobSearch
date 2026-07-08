package com.jobSearch.service;

import com.jobSearch.dto.request.RecruiterRequest;
import com.jobSearch.dto.request.UpdateRecruiterProfileRequest;
import com.jobSearch.dto.response.RecruiterResponse;
import com.jobSearch.entity.Recruiter;
import com.jobSearch.entity.User;
import com.jobSearch.enums.Role;
import com.jobSearch.exception.DuplicateResourceException;
import com.jobSearch.exception.ResourceNotFoundException;
import com.jobSearch.repository.RecruiterRepo;
import com.jobSearch.repository.UserRepo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;

@Service
public class RecruiterService {
    private final RecruiterRepo recruiterRepo;
    private final UserRepo userRepo;

    public RecruiterService(RecruiterRepo recruiterRepo,
                            UserRepo userRepo) {
        this.recruiterRepo = recruiterRepo;
        this.userRepo = userRepo;
    }

    public RecruiterResponse createRecruiter(RecruiterRequest recruiterRequest) {


        User user = getCurrentUser();
        // create recruiter entity
        if(recruiterRepo.existsByUserId(user.getId())){
            throw new DuplicateResourceException("Recruiter profile already exists");
        }

        Recruiter recruiter = new Recruiter();
        recruiter.setCompanyDescription(recruiterRequest.getCompanyDescription());
        recruiter.setPhone(recruiterRequest.getPhone());
        recruiter.setCompanyName(recruiterRequest.getCompanyName());
        recruiter.setUserId(user.getId());

        LocalDateTime now = LocalDateTime.now();

        recruiter.setCreatedAt(now);
        recruiter.setUpdatedAt(now);
        recruiter.setFullName(recruiterRequest.getFullName());

        // NOTE: @Transactional removed — MongoDB transactions require replica set.
// Writes ordered by criticality: user role updated first (auth source of truth),
// recruiter profile created second (recoverable if this fails).

        user.setUpdatedAt(LocalDateTime.now());
        user.setRole(Role.RECRUITER);
        userRepo.save(user);
        Recruiter savedRecruiter = recruiterRepo.save(recruiter);

        // create response
     return mapToResponse(savedRecruiter,user.getEmail());
    }
    public RecruiterResponse getProfile() {
     User user = getCurrentUser();
        Recruiter  recruiter = recruiterRepo.findByUserId(user.getId()).orElseThrow(() -> new ResourceNotFoundException("recruiter profile not found"));
        return  mapToResponse(recruiter,user.getEmail());

    }

    public RecruiterResponse updateProfile(UpdateRecruiterProfileRequest request) {
        User user = getCurrentUser();
        Recruiter recruiter = recruiterRepo.findByUserId(user.getId()).orElseThrow(() -> new ResourceNotFoundException("Recruiter not found"));
        if (request.getFullName() != null) {
            recruiter.setFullName(request.getFullName());
        }

        if (request.getCompanyName() != null) {
            recruiter.setCompanyName(request.getCompanyName());
        }

        if (request.getCompanyDescription() != null) {
            recruiter.setCompanyDescription(request.getCompanyDescription());
        }

        if (request.getPhone() != null) {
            recruiter.setPhone(request.getPhone());
        }
        recruiter.setUpdatedAt(LocalDateTime.now());
        Recruiter updatedRecruiter = recruiterRepo.save(recruiter);
    return  mapToResponse(updatedRecruiter,user.getEmail());
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String email = authentication.getName();

        return userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
    private RecruiterResponse mapToResponse(Recruiter recruiter, String email) {
        RecruiterResponse response = new RecruiterResponse();

        response.setFullName(recruiter.getFullName());
        response.setCompanyName(recruiter.getCompanyName());
        response.setCompanyDescription(recruiter.getCompanyDescription());
        response.setPhone(recruiter.getPhone());
        response.setEmail(email);
        response.setCreatedAt(recruiter.getCreatedAt());
        response.setUpdatedAt(recruiter.getUpdatedAt());

        return response;
    }


}
