package org.example.backend.service;

import org.example.backend.model.dto.ArticlesDto;
import org.example.backend.model.plp.ArticlesPlpDto;
import org.example.backend.model.record.Articles;
import org.example.backend.repository.ArticlesRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class ArticlesServiceTest {
    @Mock
    ArticlesRepository articlesRepository;

    @InjectMocks
    ArticlesService articlesService;
    AutoCloseable mocks;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
    }
    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }
    LocalDateTime date = LocalDateTime.of(1221, 1, 12, 0, 0);

    @Test
    void createArticles() {

        ArticlesDto articles = new ArticlesDto("name", "img", "description");
         Articles expected = new Articles("id", articles.name(), articles.imgUrl(), articles.description());
        when(articlesRepository.save(ArgumentMatchers.any(Articles.class))).thenReturn(expected);
        Articles result = articlesService.createArticles(articles);
        verify(articlesRepository).save(ArgumentMatchers.any(Articles.class));
        assertEquals(expected, result);
    }

    @Test
    void updateArticles() {
        Articles oldData = new Articles("id","name2", "img2", "description2" );

        ArticlesDto updateData  = new ArticlesDto("name", "img", "description2" );

        Articles updatedData = new Articles("id","name2", "img2", "description2");

        when(articlesRepository.findById("id")).thenReturn(Optional.of(oldData));
        when(articlesRepository.save(any(Articles.class))).thenReturn(updatedData);
        Articles result = articlesService.updateArticles("id", updateData);
        assertNotNull(result);
        verify(articlesRepository).save(any(Articles.class));
    }

    @Test
    void deleteArticles() {
        String id = "id";

        Articles articles = new Articles(id,"name2", "img2", "description2");when(articlesRepository.findById(id)).thenReturn(Optional.of(articles));
        articlesService.deleteArticles(id);
        assertEquals(id, articles.id());
        verify(articlesRepository).delete(articles);
    }

    @Test
    void findAllPlp() {
        List<Articles> list = List.of(
                new Articles("id1", "name1", "img1", "description1"),
                new Articles("id2", "name2", "img2", "description2")
        );

        when(articlesRepository.findAll()).thenReturn(list);

        List<ArticlesPlpDto> result = articlesService.findAllPlp();

        assertEquals(2, result.size());

        ArticlesPlpDto firstDto = result.get(0);
        ArticlesPlpDto secondDto = result.get(1);

        assertEquals("id1", firstDto.id());
        assertEquals("name1", firstDto.name());
        assertEquals("img1", firstDto.imgUrl());
        assertEquals("description1", firstDto.description());

        assertEquals("id2", secondDto.id());
        assertEquals("name2", secondDto.name());
        assertEquals("img2", secondDto.imgUrl());
        assertEquals("description2", secondDto.description());

        verify(articlesRepository).findAll();
    }


    @Test
    void findById() {
        String id = "id";

        Articles articles = new Articles(id,"name2", "img2", "description2");
        when(articlesRepository.findById(id)).thenReturn(Optional.of(articles));
        articlesService.findById(id);
        assertEquals(id, articles.id());
        verify(articlesRepository).findById(id);
    }

}