package com.jobSearch.service;

import com.jobSearch.dto.request.CreateJobRequest;
import com.jobSearch.dto.request.UpdateJobRequest;
import com.jobSearch.dto.response.JobResponse;
import com.jobSearch.entity.Job;
import com.jobSearch.entity.Recruiter;
import com.jobSearch.entity.User;
import com.jobSearch.enums.JobStatus;
import com.jobSearch.enums.Role;
import com.jobSearch.exception.ResourceNotFoundException;
import com.jobSearch.repository.ApplicationRepo;
import com.jobSearch.repository.JobRepo;
import com.jobSearch.repository.RecruiterRepo;
import com.jobSearch.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.function.Supplier;

@Service
public class JobService {
    private final JobRepo  jobRepo;
    private final UserRepo userRepo;
    private final RecruiterRepo recruiterRepo;
    private final ApplicationRepo applicationRepo;
    public JobService(JobRepo jobRepo, UserRepo userRepo, RecruiterRepo recruiterRepo , ApplicationRepo applicationRepo ) {
        this.jobRepo = jobRepo;
        this.userRepo = userRepo;
        this.recruiterRepo = recruiterRepo;
        this.applicationRepo = applicationRepo;

    }



    public JobResponse createJob(CreateJobRequest createJobRequest) {
        User user = getCurrentUser();
        Recruiter recruiter = recruiterRepo.findByUserId(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Recruiter profile not found"));

        Job job = new Job();
        job.setJobType(createJobRequest.getJobType());
        job.setDescription(createJobRequest.getDescription());
        job.setTitle(createJobRequest.getTitle());
        job.setLocation(createJobRequest.getLocation());
        job.setSalary(createJobRequest.getSalary());
        job.setExperienceRequired(createJobRequest.getExperienceRequired());
        job.setDeadline(createJobRequest.getDeadline());
        job.setVacancies(createJobRequest.getVacancies());
        LocalDateTime now = LocalDateTime.now();

        job.setCreatedAt(now);
        job.setUpdatedAt(now);
        job.setRecruiterId(recruiter.getId());
        job.setStatus(JobStatus.OPEN);

        Job savedJob = jobRepo.save(job);


        return mapToResponse(savedJob) ;

    }
    public JobResponse updateJob(String jobId ,UpdateJobRequest updateJobRequest) {
        User user = getCurrentUser();
        Recruiter recruiter = recruiterRepo.findByUserId(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Recruiter profile not found"));
        Job job = jobRepo.findById(new ObjectId(jobId))
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));

     if( job.getRecruiterId().equals(recruiter.getId())){
         if(updateJobRequest.getJobType() != null){
             job.setJobType(updateJobRequest.getJobType());
         }  if (updateJobRequest.getTitle() != null) {
             job.setTitle(updateJobRequest.getTitle());
         } if (updateJobRequest.getLocation() != null) {
             job.setLocation(updateJobRequest.getLocation());
         }  if (updateJobRequest.getSalary() != null) {
             job.setSalary(updateJobRequest.getSalary());
         }  if (updateJobRequest.getDescription() != null) {
             job.setDescription(updateJobRequest.getDescription());
         }  if (updateJobRequest.getExperienceRequired() != null) {
             job.setExperienceRequired(updateJobRequest.getExperienceRequired());
         }  if (updateJobRequest.getVacancies() != null) {
             job.setVacancies(updateJobRequest.getVacancies());
         } if (updateJobRequest.getDeadline() != null) {
             job.setDeadline(updateJobRequest.getDeadline());
         }  if (updateJobRequest.getStatus() != null) {
             job.setStatus(updateJobRequest.getStatus());
         }
     }else throw new AccessDeniedException("You are not allowed to update this job.");
        job.setUpdatedAt(LocalDateTime.now());
        Job save = jobRepo.save(job);
        return mapToResponse(save);
    }
    public JobResponse getJobById(String jobId) {


        Job job = jobRepo.findById(new ObjectId(jobId))
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));

        return  mapToResponse(job);
    }
    public void deleteJobById(String jobId) {
        User user = getCurrentUser();
        Recruiter recruiter = recruiterRepo.findByUserId(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Recruiter profile not found"));

        Job job = jobRepo.findById(new ObjectId(jobId))
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));
        if (!job.getRecruiterId().equals(recruiter.getId())) {
            throw new AccessDeniedException("You are not allowed to delete this job.");
        }

// Business rule: deleting a job also deletes all associated applications
        applicationRepo.deleteByJobId(job.getId());

        jobRepo.delete(job);
        return;
    }
    public Page<JobResponse> getAll(Pageable pageable) {

        Page<Job> jobs = jobRepo.findAll(pageable);

        return jobs.map(this::mapToResponse);
    }


   private User getCurrentUser() {
        Authentication  authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();
        return userRepo.findByEmail(user).orElseThrow(()->new ResourceNotFoundException("User  not found"));

    }
    private JobResponse mapToResponse(Job savedJob) {
        JobResponse response = new JobResponse();
        response.setJobType(savedJob.getJobType());
        response.setDescription(savedJob.getDescription());
        response.setTitle(savedJob.getTitle());
        response.setLocation(savedJob.getLocation());
        response.setSalary(savedJob.getSalary());
        response.setExperienceRequired(savedJob.getExperienceRequired());
        response.setDeadline(savedJob.getDeadline());
        response.setVacancies(savedJob.getVacancies());
        response.setId(savedJob.getId());
        response.setStatus(savedJob.getStatus());
        response.setCreatedAt(savedJob.getCreatedAt());
        response.setUpdatedAt(savedJob.getUpdatedAt());

        return response ;
    }
}
