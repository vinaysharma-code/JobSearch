package com.jobSearch.repository;

import com.jobSearch.entity.Job;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobRepo extends MongoRepository<Job, ObjectId> {
}
