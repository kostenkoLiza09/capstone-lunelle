package org.example.backend.config;

import org.example.backend.model.record.Perfume;
import org.example.backend.repository.PerfumeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PerfumeIndexCronService {

    private static final Logger logger = LoggerFactory.getLogger(PerfumeIndexCronService.class);

    private final PerfumeRepository perfumeRepository;
    private final ElasticsearchOperations elasticsearchOperations;
    private final PerfumeIndexRepository perfumeIndexRepository;

    public PerfumeIndexCronService(PerfumeRepository perfumeRepository, ElasticsearchOperations elasticsearchOperations, PerfumeIndexRepository perfumeIndexRepository) {
        this.perfumeRepository = perfumeRepository;
        this.elasticsearchOperations = elasticsearchOperations;
        this.perfumeIndexRepository = perfumeIndexRepository;
    }

    @Scheduled(cron = "0 0/20 * * * *")
    public void syncToElastic() {
        logger.info("Sync to Elastic started");
        elasticsearchOperations.indexOps(PerfumeIndex.class).refresh();
        Collection<PerfumeIndex> perfumeIndices = generatePerfumeIndices();
        logger.info("Perfume indices to save: {}", perfumeIndices.size());
        perfumeIndexRepository.deleteAll();
        perfumeIndexRepository.saveAll(perfumeIndices);
        elasticsearchOperations.indexOps(PerfumeIndex.class).refresh();
        logger.info("Sync to Elastic finished");
    }

    private Collection<PerfumeIndex> generatePerfumeIndices() {
        Collection<Perfume> perfumes = perfumeRepository.findAll();

        return perfumes.stream().map(perfume -> {
            PerfumeIndex index = new PerfumeIndex();
            index.setId(perfume.id());
            StringBuilder info = new StringBuilder();
            info.append(perfume.name());
            if (perfume.brand() != null) {
                info.append(", ").append(perfume.brand());
            }
            if (perfume.perfumeFamily() != null) {
                info.append(", ").append(perfume.perfumeFamily());
            }
            if (perfume.notes() != null && !perfume.notes().isEmpty()) {
                info.append(", notes: ");
                String notesStr = perfume.notes().stream()
                        .map(Enum::name)
                        .reduce((a, b) -> a + ", " + b)
                        .orElse("");
                info.append(notesStr);
            }
            index.setPerfumeInfo(info.toString());
            return index;
        }).toList();
    }
}
