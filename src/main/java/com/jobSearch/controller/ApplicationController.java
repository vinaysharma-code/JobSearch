package com.jobSearch.controller;

import com.jobSearch.service.ApplicationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/applications")
public class ApplicationController {
   @Autowired
    private ApplicationsService applicationsService;



   @GetMapping("/my")
    private ResponseEntity<?> getAllApplications(){

   }
   @PutMapping("/{applicationId}/status")
    private ResponseEntity<?> updateApplication(@PathVariable String applicationId){

   }
   @PutMapping("/{applicationId}/withdraw")
    private ResponseEntity<?> withdrawApplication(@PathVariable String applicationId){

   }
}
