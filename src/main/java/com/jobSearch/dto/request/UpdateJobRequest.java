package com.jobSearch.dto.request;

import com.jobSearch.enums.JobStatus;
import com.jobSearch.enums.JobType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Data
public class UpdateJobRequest {
    @Size(min = 1, max = 25)
    private ObjectId id ;
    private String  title ;

    @Size(min = 1, max = 100)
    private String description;

    @Size(min = 1, max = 100)
    private String location;

    @Positive
    private Double salary ;

    private JobType jobType;
    @Positive
    private Integer experienceRequired ;

    private LocalDateTime deadline ;

    @Positive
    private Integer vacancies ;

    private JobStatus status;

}
