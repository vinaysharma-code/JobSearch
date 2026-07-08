package com.jobSearch.repository;

import com.jobSearch.entity.Job;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface JobRepo extends MongoRepository<Job, ObjectId> {

    Optional<Job> findById(ObjectId id);
}
