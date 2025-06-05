package org.example.backend.repository;

import org.example.backend.model.enums.Selection;
import org.example.backend.model.record.Perfume;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerfumeRepository extends MongoRepository<Perfume, String>{

    List<Perfume> findBySelection(Selection selection);

}
