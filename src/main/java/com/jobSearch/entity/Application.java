package com.jobSearch.entity;

import com.jobSearch.enums.ApplicationStatus;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Data
@Document(collection = "applications")
@CompoundIndex(name = "job_applicant_unique", def = "{'jobId': 1, 'applicantId': 1}", unique = true)
public class Application {
    @Id
    private ObjectId id;
    private ObjectId jobId;
    private ObjectId applicantId;
    private ApplicationStatus status;
    private String coverLetter ;
    private LocalDateTime appliedAt ;
    private LocalDateTime updatedAt ;
}
