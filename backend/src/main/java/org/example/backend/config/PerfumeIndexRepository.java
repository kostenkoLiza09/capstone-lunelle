package org.example.backend.config;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerfumeIndexRepository extends ElasticsearchRepository<PerfumeIndex, String> {
    List<PerfumeIndex> findByPerfumeInfoContainingIgnoreCase(String query);
}
