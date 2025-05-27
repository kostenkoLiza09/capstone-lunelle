package org.example.backend.service;

import org.example.backend.model.dto.PerfumeDto;
import org.example.backend.model.enums.*;
import org.example.backend.model.record.Perfume;
import org.example.backend.model.record.PerfumeVariant;
import org.example.backend.repository.PerfumeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class PerfumeServiceTest {



    @Test
    void addPerfume() {
        List<PerfumeVariant> variants = List.of(
                new PerfumeVariant(Volume.ML30, 49.99f),
                new PerfumeVariant(Volume.ML50, 69.99f)
        );

        List<Season> seasons = List.of(
                Season.SPRING,
                Season.SUMMER
        );

        List<Notes> notes = List.of(
                Notes.COFFEE,
                Notes.ROSE
        );

        PerfumeDto perfume = new PerfumeDto(
                "name", "imageURL" , "description",
                variants, Selection.WOMAN, Brand.ARMANI, PerfumeFamily.AROMATIC,
                seasons, notes);

        PerfumeRepository perfumeRepository = mock(PerfumeRepository.class);
        PerfumeService perfumeService = new PerfumeService(perfumeRepository);

        Perfume expected = new Perfume(
                "id",
                perfume.name(),
                perfume.imageURL(),
                perfume.description(),
                variants,
                perfume.selection(),
                perfume.brand(),
                perfume.perfumeFamily(),
                seasons,
                notes
        );

        when(perfumeRepository.save(ArgumentMatchers.any(Perfume.class))).thenReturn(expected);
        Perfume result = perfumeService.addPerfume(perfume);
        verify(perfumeRepository).save(ArgumentMatchers.any(Perfume.class));
        assertEquals(expected, result);







    }

}