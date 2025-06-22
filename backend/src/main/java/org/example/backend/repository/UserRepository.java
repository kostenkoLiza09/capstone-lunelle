package org.example.backend.repository;

import org.example.backend.model.record.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<AppUser, String> {}

