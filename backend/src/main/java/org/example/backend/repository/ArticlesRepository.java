package org.example.backend.repository;

import org.example.backend.model.record.Articles;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticlesRepository extends MongoRepository<Articles, String> {


}
