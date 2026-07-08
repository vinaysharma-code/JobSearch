package com.jobSearch.dto.response;

import com.jobSearch.enums.JobStatus;
import com.jobSearch.enums.JobType;
import lombok.Data;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
@Data
public class JobResponse {
    private ObjectId id ;
    private String  title ;
    private String description ;
    private String location;
    private double salary ;
    private JobType jobType;
    private int experienceRequired ;
    private LocalDateTime deadline ;
    private int vacancies;
    private JobStatus status ;
    private LocalDateTime createdAt ;
    private LocalDateTime updatedAt ;

}
