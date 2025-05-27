package org.example.backend.repository;

import org.example.backend.model.record.Perfume;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfumeRepository extends MongoRepository<Perfume, String>{
}
