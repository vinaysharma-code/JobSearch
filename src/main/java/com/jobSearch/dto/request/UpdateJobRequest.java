package com.jobSearch.dto.request;

import com.jobSearch.enums.JobStatus;
import com.jobSearch.enums.JobType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;
@Data
public class UpdateJobRequest {

    @NotBlank
    @Size(max = 20)
    private String  title ;
    @NotBlank
    @Size(max = 100)
    private String description ;
    @NotBlank
    @Size(max = 100)
    private String location;
    @Positive
    @Size(max = 100)
    private double salary ;
    @Size(max = 100)
    @NonNull
    private JobType jobType;
    @Positive
    @Size(max = 100)
    private int experienceRequired ;
    @NonNull
    @Size(max = 100)
    private LocalDateTime deadline ;
    @Positive
    @Size(max = 100)
    private int vacancies ;
    @NonNull
    @Size(max = 100)
    private JobStatus status ;
}
