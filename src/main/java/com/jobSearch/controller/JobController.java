package com.jobSearch.controller;


import com.jobSearch.dto.request.CreateJobRequest;
import com.jobSearch.dto.request.UpdateJobRequest;
import com.jobSearch.dto.response.ApplicationResponse;
import com.jobSearch.dto.response.JobResponse;
import com.jobSearch.service.ApplicationsService;
import com.jobSearch.service.JobService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;
    private final ApplicationsService  applicationsService;
    public JobController(JobService jobService , ApplicationsService applicationsService    ) {
        this.jobService = jobService;
        this.applicationsService = applicationsService;
    }


    @PostMapping
    public ResponseEntity<?> addJob(@Valid @RequestBody CreateJobRequest createJobRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(jobService.createJob(createJobRequest));
    }
    @PutMapping("/{jobId}")
    public ResponseEntity<JobResponse> updateJob(@PathVariable String jobId,@Valid @RequestBody UpdateJobRequest updateJobRequest){
  return ResponseEntity.status(HttpStatus.OK).body(jobService.updateJob(jobId,updateJobRequest));
    }
    @GetMapping("/{jobId}")
   public ResponseEntity<?> getJobById( @PathVariable String jobId){
        return ResponseEntity.status(HttpStatus.OK).body(jobService.getJobById(jobId));
    }
    @GetMapping
    public ResponseEntity<Page<JobResponse>> getAllJobs(Pageable pageable) {

        return ResponseEntity.ok(jobService.getAll(pageable));
    }
    @DeleteMapping("/{jobId}")
    public ResponseEntity<?> deleteJob(@PathVariable String jobId){
        jobService.deleteJobById(jobId);
        return ResponseEntity.noContent().build();
    }
    // JobController
    @PostMapping("/{jobId}/apply")
    public ResponseEntity<ApplicationResponse> applyForJob(
            @PathVariable String jobId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(applicationsService.applyForJob(jobId));
    }

}
