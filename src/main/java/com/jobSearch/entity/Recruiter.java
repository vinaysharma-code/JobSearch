package com.jobSearch.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Data
@Document(collection = "recruiter")
public class Recruiter {
    @Id
    private ObjectId id;;
    private ObjectId userId;
    private String fullName;
    private String companyName;
    private String companyDescription;
    private String phone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;





}
