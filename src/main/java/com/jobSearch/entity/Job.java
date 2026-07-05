package com.jobSearch.entity;

import com.jobSearch.enums.JobStatus;
import com.jobSearch.enums.JobType;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDateTime;
@Data
@Document(collection = "jobs")
public class Job {
     @Id
     private ObjectId id ;
     private String  title ;
     private String description ;
     private String location;
     private double salary ;
     private JobType jobType;
     private int experienceRequired ;
     private LocalDateTime deadline ;
     private int vacancies ;
     private JobStatus status ;
     private ObjectId recruiterId ;
    private LocalDateTime createdAt ;
    private  LocalDateTime updatedAt ;
}
