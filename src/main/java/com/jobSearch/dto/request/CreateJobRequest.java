package com.jobSearch.dto.request;

import com.jobSearch.enums.JobType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
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
   @NonNull
    private double salary ;


    @NonNull

    private JobType jobType;

   @NonNull
    @Positive
    private int experienceRequired ;



    @NonNull
    private LocalDateTime deadline ;


    @NonNull
    @Positive
    private int vacancies ;
}
