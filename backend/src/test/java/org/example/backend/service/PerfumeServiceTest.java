package org.example.backend.service;

import org.example.backend.exception.PerfumeNotFoundException;
import org.example.backend.model.dto.PerfumeDto;
import org.example.backend.model.enums.*;
import org.example.backend.model.plp.PerfumePlpDto;
import org.example.backend.model.record.Perfume;
import org.example.backend.model.record.PerfumeVariant;
import org.example.backend.repository.PerfumeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class PerfumeServiceTest {

    @Mock
    PerfumeRepository perfumeRepository;

    @InjectMocks
    PerfumeService perfumeService;
    AutoCloseable mocks;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
    }
    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    void addPerfume() {
        List<PerfumeVariant> variants = List.of(new PerfumeVariant(Volume.ML30, 49.99f),
                new PerfumeVariant(Volume.ML50, 69.99f));

        List<Season> seasons = List.of(Season.SPRING, Season.SUMMER);
        List<Notes> notes = List.of(Notes.COFFEE, Notes.ROSE);
        PerfumeDto perfume = new PerfumeDto(
                "name", "imageURL" , "description",
                variants, Selection.WOMAN, Brand.ARMANI, PerfumeFamily.AROMATIC,
                seasons, notes);

        Perfume expected = new Perfume("id", perfume.name(), perfume.imageURL(), perfume.description(),
                variants, perfume.selection(), perfume.brand(), perfume.perfumeFamily(), seasons, notes);

        when(perfumeRepository.save(ArgumentMatchers.any(Perfume.class))).thenReturn(expected);
        Perfume result = perfumeService.addPerfume(perfume);
        verify(perfumeRepository).save(ArgumentMatchers.any(Perfume.class));
        assertEquals(expected, result);
    }


    @Test
    void updatePerfume(){
        List<PerfumeVariant> variants = List.of(new PerfumeVariant(Volume.ML20, 50.9f));
        List<Season> seasons = List.of(Season.WINTER, Season.SPRING);
        List<Notes> notes = List.of(Notes.MUSK, Notes.OUD);

        Perfume oldData = new Perfume("id", "name", "imageURL" , "description",
                variants, Selection.WOMAN, Brand.ARMANI, PerfumeFamily.AROMATIC, seasons, notes);

        List<PerfumeVariant> newVariants = List.of(
                new PerfumeVariant(Volume.ML50, 50.99f)
        );
        List<Season> newSeasons = List.of(Season.ALL);
        List<Notes> newNotes = List.of(Notes.JASMINE, Notes.BERGAMOT);

        PerfumeDto updateData = new PerfumeDto(
                "name", "imageURL" , "description",
                newVariants, Selection.WOMAN, Brand.ARMANI, PerfumeFamily.AROMATIC,
                newSeasons, newNotes);

        Perfume updatedData = new Perfume("id", "name", "imageURL" , "description",
                newVariants, Selection.WOMAN, Brand.ARMANI, PerfumeFamily.AROMATIC, newSeasons, newNotes);

        when(perfumeRepository.findById("id")).thenReturn(Optional.of(oldData));
        when(perfumeRepository.save(any(Perfume.class))).thenReturn(updatedData);

        Perfume result = perfumeService.updatePerfume("id", updateData);
        assertNotNull(result);
        verify(perfumeRepository).save(any(Perfume.class));
    }
    @Test
    void deletePerfumeNotFound(){
        String perfumeId = "id";
        when(perfumeRepository.findById(perfumeId)).thenReturn(Optional.empty());
        assertThrows(PerfumeNotFoundException.class, () -> perfumeService.deletePerfume(perfumeId));
        verify(perfumeRepository, never()).delete(any());

    }

    @Test
    void deletePerfumeFound (){
        String perfumeId = "id";
        List<PerfumeVariant> variants = List.of(
                new PerfumeVariant(Volume.ML30, 49.99f));
        List<Season> seasons = List.of(Season.WINTER, Season.AUTUMN);
        List<Notes> notes = List.of(Notes.AQUATIC, Notes.VANILLA);
        Perfume perfume = new Perfume("id","name", "imageURL" , "description",
                variants, Selection.WOMAN, Brand.ARMANI, PerfumeFamily.AROMATIC, seasons, notes);

        when(perfumeRepository.findById(perfumeId)).thenReturn(Optional.of(perfume));
        perfumeService.deletePerfume(perfumeId);
        assertEquals(perfumeId, perfume.id());
        verify(perfumeRepository).delete(perfume);

    }

    @Test
    void findByIdPerfume (){
        String perfumeId = "id";
        Perfume perfume = new Perfume(perfumeId, "name", "imageURL", "description",
                List.of(new PerfumeVariant(Volume.ML30, 49.99f)), Selection.WOMAN,
                Brand.ARMANI, PerfumeFamily.AROMATIC, List.of(Season.WINTER, Season.AUTUMN),
                List.of(Notes.AQUATIC, Notes.VANILLA)
        );

        when(perfumeRepository.findById(perfumeId)).thenReturn(Optional.of(perfume));
        Perfume result = perfumeService.findById(perfumeId);
        assertEquals(perfume, result);
        verify(perfumeRepository).findById(perfumeId);
    }

    @Test
    void findAllPlpList(){
        List<Perfume> listPerfume = List.of(
                new Perfume("id1", "name1", "imageURL1", "description1",
                        List.of(new PerfumeVariant(Volume.ML30, 49.99f)), Selection.WOMAN, Brand.ARMANI,
                        PerfumeFamily.AROMATIC, List.of(Season.WINTER, Season.AUTUMN), List.of(Notes.AQUATIC, Notes.VANILLA)),
                new Perfume("id2", "name2", "imageURL2", "description2",
                        List.of(new PerfumeVariant(Volume.ML50, 50.99f)), Selection.MAN, Brand.ARMANI,
                        PerfumeFamily.AROMATIC, List.of(Season.SUMMER, Season.AUTUMN), List.of(Notes.COFFEE, Notes.ROSE))
        );
        when(perfumeRepository.findAll()).thenReturn(listPerfume);

        List<PerfumePlpDto> result = perfumeService.findAllPlp();

        assertEquals(2, result.size());
        PerfumePlpDto firstDto = result.get(0);
        assertEquals("name1", firstDto.name());
        assertEquals("imageURL1", firstDto.imageURL());
        assertEquals(49.99f, firstDto.price());
        assertEquals(Volume.ML30, firstDto.volume());

        PerfumePlpDto secondDto = result.get(1);
        assertEquals("name2", secondDto.name());
        assertEquals("imageURL2", secondDto.imageURL());
        assertEquals(50.99f, secondDto.price());
        assertEquals(Volume.ML50, secondDto.volume());

        verify(perfumeRepository).findAll();
    }
}