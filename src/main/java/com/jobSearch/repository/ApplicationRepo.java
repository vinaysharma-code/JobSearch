package com.jobSearch.repository;


import com.jobSearch.entity.Application;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import java.lang.ScopedValue;
import java.util.List;

@Repository
public interface ApplicationRepo extends MongoRepository<Application, ObjectId> {

    void deleteByJobId(ObjectId jobId);

    List<Application> findByJobId(ObjectId jobId);
    boolean existsByApplicantIdAndJobId(ObjectId applicantId, ObjectId jobId);

    List<Application> findAllByJobId(ObjectId jobId);
    List<Application> findAllByApplicantId(ObjectId applicantId);
}

