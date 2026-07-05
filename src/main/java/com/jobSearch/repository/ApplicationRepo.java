package com.jobSearch.repository;


import com.jobSearch.entity.Application;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApplicationRepo extends MongoRepository<Application, ObjectId> {
}
