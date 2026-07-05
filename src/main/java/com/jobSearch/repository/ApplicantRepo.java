package com.jobSearch.repository;

import com.jobSearch.entity.Applicant;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ApplicantRepo extends MongoRepository<Applicant, ObjectId> {

}
