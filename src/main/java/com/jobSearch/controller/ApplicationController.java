package com.jobSearch.controller;

import com.jobSearch.dto.request.UpdateApplicationStatusRequest;
import com.jobSearch.dto.response.ApplicationResponse;
import com.jobSearch.service.ApplicationsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    private final ApplicationsService applicationsService;

   public ApplicationController(ApplicationsService applicationsService) {
       this.applicationsService = applicationsService;
   }

   @GetMapping("/my")
   public ResponseEntity<?> getAllApplications(){
return ResponseEntity.ok(applicationsService.getMyApplications());
   }
   @PutMapping("/{applicationId}/status")
   public ResponseEntity<?> updateApplication(@PathVariable String applicationId ,@Valid @RequestBody UpdateApplicationStatusRequest update){
      return ResponseEntity.ok(applicationsService.updateApplicationStatus(applicationId,update));
   }
   @PutMapping("/{applicationId}/withdraw")
   public ResponseEntity<?> withdrawApplication(@PathVariable String applicationId){
return ResponseEntity.ok(applicationsService.withdrawApplication(applicationId));
   }

   @GetMapping("/job/{jobId}")
   public ResponseEntity<List<ApplicationResponse>>
   getApplicationsForJob(@PathVariable String jobId) {

      return ResponseEntity.ok(
              applicationsService.getApplicationsForJob(jobId)
      );
   }


}
