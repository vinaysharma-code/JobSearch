package com.jobSearch.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "applicant")
public class Applicant {
    @Id
  private ObjectId id;
  private ObjectId  userId;
  private String  fullName;
  private String phone;
  private List<String> skills;
  private String education;
  private int experience;
  private String city;
  private String linkedinUrl ;
  private String githubUrl;
  private String resumeUrl;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
