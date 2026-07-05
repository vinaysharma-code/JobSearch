package com.jobSearch.controller;


import com.jobSearch.dto.request.UpdateJobRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @GetMapping("/all")
    public ResponseEntity<?> getAllJobs(){

    }
    @PostMapping
    private ResponseEntity<?> addJob(){

    }
    @PutMapping("/{jobId}")
    private ResponseEntity updateJob( @PathVariable String jobId,@RequestBody UpdateJobRequest updateJobRequest){

    }
    @GetMapping("/{jobId}")
    private ResponseEntity<?> getJobById(@PathVariable String jobId){

    }
    @DeleteMapping("/{jobId}")
    private ResponseEntity<?> deleteJob(@PathVariable String jobId){

    }
    @PostMapping("/{jobId}/apply")
    private ResponseEntity<?> applyForJob(@PathVariable String jobId){

    }
}
