package com.jobSearch.dto.request;

import com.jobSearch.enums.JobType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;
@Data
public class CreateJobRequest {
    @NotBlank
    @Size(min = 1, max = 25)
    private String  title ;

    @NotBlank
    @Size(min = 1, max = 100)
    private String description;


    @NotBlank
    @Size(min = 1, max = 100)
    private String location;


   @Positive
    @Size(min = 1, max = 10)
    private double salary ;


    @NonNull
    @Size(min = 1, max = 20)
    private JobType jobType;

    @Size(max = 100)
    @Positive
    private int experienceRequired ;


    @Size(max = 20)
    @NonNull
    private LocalDateTime deadline ;


    @Size(max = 100)
    @Positive
    private int vacancies ;
}
