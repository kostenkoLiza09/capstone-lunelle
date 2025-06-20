package org.example.backend.config;

import org.example.backend.model.enums.Notes;
import org.example.backend.model.record.Perfume;
import org.example.backend.repository.PerfumeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.mockito.Mockito.*;
@ActiveProfiles("test")
class PerfumeIndexCronServiceTest {

    private PerfumeRepository perfumeRepository;
    private ElasticsearchOperations elasticsearchOperations;
    private IndexOperations indexOperations;
    private PerfumeIndexRepository perfumeIndexRepository;

    private PerfumeIndexCronService service;

    @BeforeEach
    void setUp() {
        perfumeRepository = mock(PerfumeRepository.class);
        elasticsearchOperations = mock(ElasticsearchOperations.class);
        indexOperations = mock(IndexOperations.class);
        perfumeIndexRepository = mock(PerfumeIndexRepository.class);

        when(elasticsearchOperations.indexOps(PerfumeIndex.class)).thenReturn(indexOperations);

        service = new PerfumeIndexCronService(perfumeRepository, elasticsearchOperations, perfumeIndexRepository);
    }

    @Test
    void testSyncToElastic() {
        Perfume perfume1 = Mockito.mock(Perfume.class);
        when(perfume1.id()).thenReturn("1");
        when(perfume1.name()).thenReturn("Perfume1");
        when(perfume1.brand()).thenReturn(null);
        when(perfume1.perfumeFamily()).thenReturn(null);

        // Вот здесь важно: явно указываем тип Set<Notes>
        List<Notes> emptyNotes = List.of();
        when(perfume1.notes()).thenReturn(emptyNotes);

        when(perfumeRepository.findAll()).thenReturn(List.of(perfume1));

        service.syncToElastic();

        verify(indexOperations, times(2)).refresh();
        verify(perfumeIndexRepository).deleteAll();
        verify(perfumeIndexRepository).saveAll(any());
    }
}