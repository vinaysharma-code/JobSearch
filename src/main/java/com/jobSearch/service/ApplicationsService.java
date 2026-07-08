package com.jobSearch.service;


import com.jobSearch.dto.request.UpdateApplicationStatusRequest;
import com.jobSearch.dto.response.ApplicationResponse;


import com.jobSearch.entity.*;
import com.jobSearch.enums.ApplicationStatus;
import com.jobSearch.enums.JobStatus;

import com.jobSearch.exception.DuplicateResourceException;
import com.jobSearch.exception.ResourceNotFoundException;
import com.jobSearch.exception.BadRequestException;
import com.jobSearch.repository.*;
import org.bson.types.ObjectId;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApplicationsService {
    private final ApplicationRepo applicationRepo;
    private final UserRepo userRepo;
    private final ApplicantRepo applicantRepo;
    private final JobRepo jobRepo;
    private final RecruiterRepo recruiterRepo;

    public ApplicationsService(ApplicationRepo applicationRepo , RecruiterRepo recruiterRepo, UserRepo userRepo,ApplicantRepo applicantRepo, JobRepo jobRepo) {
        this.applicationRepo = applicationRepo;
        this.userRepo = userRepo;
        this.applicantRepo = applicantRepo;
        this.jobRepo = jobRepo;
        this.recruiterRepo = recruiterRepo;
    }
    public ApplicationResponse applyForJob(String jobId){
    User user = getCurrentUser();
    Applicant applicant =
            applicantRepo.findByUserId(user.getId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Applicant profile not found"));
    Job job =
            jobRepo.findById(new ObjectId(jobId))
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Job not found"));
        if(job.getStatus() != JobStatus.OPEN){
            throw new BadRequestException(
                    "Job is closed");
        }
        if(applicationRepo.existsByApplicantIdAndJobId(applicant.getId(), new ObjectId(jobId))) {

            throw new DuplicateResourceException(
                    "Already applied");
        }
        Application application =
                new Application();

        application.setApplicantId(applicant.getId());
        application.setJobId(job.getId());


        application.setStatus(
                ApplicationStatus.APPLIED);

        LocalDateTime now = LocalDateTime.now();

        application.setAppliedAt(now);
        application.setUpdatedAt(now);
        Application saved =
                applicationRepo.save(application);
        return mapToResponse(saved);

  }
  public ApplicationResponse updateApplicationStatus(String applicationId ,UpdateApplicationStatusRequest request ){
        User user =  getCurrentUser();
      Recruiter recruiter = recruiterRepo.findByUserId(user.getId()).orElseThrow(() -> new ResourceNotFoundException("Recruiter not found"));
      Application application =
              applicationRepo.findById(new ObjectId(applicationId))
                      .orElseThrow(() ->
                              new ResourceNotFoundException(
                                      "Application not found"));
      Job job = jobRepo.findById(application.getJobId())
              .orElseThrow(() -> new ResourceNotFoundException("Job not found"));

      if (!job.getRecruiterId().equals(recruiter.getId())) {
          throw new AccessDeniedException("You cannot update this application.");
      }
      if (request.getStatus() == ApplicationStatus.WITHDRAWN) {
          throw new BadRequestException(
                  "Recruiters cannot withdraw applications.");
      }
      application.setStatus(
              request.getStatus());

      application.setUpdatedAt(
              LocalDateTime.now());
      Application saved =
              applicationRepo.save(application);
     return mapToResponse(saved);
  }
  public ApplicationResponse withdrawApplication(String applicationId){
        User user = getCurrentUser();
        Applicant applicant = applicantRepo.findByUserId(user.getId()).orElseThrow(() -> new ResourceNotFoundException("Applicant not found"));
      Application application = applicationRepo.findById(new ObjectId(applicationId))
              .orElseThrow(() -> new ResourceNotFoundException("Application not found"));

      if (!application.getApplicantId().equals(applicant.getId())) {
          throw new AccessDeniedException("You cannot withdraw this application.");
      }
      if (application.getStatus() == ApplicationStatus.WITHDRAWN ||
              application.getStatus() == ApplicationStatus.SELECTED) {
          throw new BadRequestException("Cannot withdraw this application.");
      }
      application.setStatus(ApplicationStatus.WITHDRAWN);
      application.setUpdatedAt(LocalDateTime.now());
      Application save = applicationRepo.save(application);

      return mapToResponse(save);

  }
    public List<ApplicationResponse>
    getApplicationsForJob(String jobId) {

        User user = getCurrentUser();

        Recruiter recruiter =
                recruiterRepo.findByUserId(user.getId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Recruiter not found"));

        Job job =
                jobRepo.findById(new ObjectId(jobId))
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Job not found"));

        if (!job.getRecruiterId().equals(user.getId())) {
            throw new AccessDeniedException(
                    "You cannot view applications for this job.");
        }

        List<Application> applications =
                applicationRepo.findAllByJobId(job.getId());

        return applications.stream()
                .map(this::mapToResponse)
                .toList();
    }
    public List<ApplicationResponse> getMyApplications() {
        User user = getCurrentUser();
        Applicant applicant = applicantRepo.findByUserId(user.getId()).orElseThrow(() -> new ResourceNotFoundException("Applicant not found"));
         List<Application> applications = applicationRepo.findAllByApplicantId( applicant.getId());
        return applications.stream()
                .map(this::mapToResponse)
                .toList();
     }


    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();
        return userRepo.findByEmail(user).orElseThrow(()->new ResourceNotFoundException("User  not found"));

    }
    private ApplicationResponse mapToResponse(Application application) {
        ApplicationResponse response = new ApplicationResponse();

        response.setApplicationId(
                application.getId().toHexString());

        response.setJobId(
                application.getJobId().toHexString());

        response.setStatus(
                application.getStatus());

        response.setAppliedAt(
                application.getAppliedAt());

        response.setUpdatedAt(
                application.getUpdatedAt());

        Job job = jobRepo.findById(application.getJobId())
                .orElse(null);

        if (job != null) {
            response.setJobTitle(job.getTitle());

            Recruiter recruiter =
                    recruiterRepo
                            .findByUserId(job.getRecruiterId())
                            .orElse(null);

            if (recruiter != null) {
                response.setCompanyName(
                        recruiter.getCompanyName());
            }
        }

        return response;
    }


}
