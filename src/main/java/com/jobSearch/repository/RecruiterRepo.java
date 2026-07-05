package com.jobSearch.repository;

import com.jobSearch.entity.Recruiter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RecruiterRepo extends MongoRepository<Recruiter, ObjectId> {
    boolean existsByUserId(ObjectId userId);
    Optional<Recruiter> findByUserId(ObjectId userId);
}
