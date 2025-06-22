package org.example.backend.controller;

import org.example.backend.model.dto.ArticlesDto;

import org.example.backend.model.plp.ArticlesPlpDto;
import org.example.backend.model.record.Articles;

import org.example.backend.service.ArticlesService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticlesController.class)
@AutoConfigureMockMvc(addFilters = false)
class ArticlesControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ArticlesService articlesService;

    @TestConfiguration
    static class Config {
        @Bean
        public ArticlesService articlesService() {
            return Mockito.mock(ArticlesService.class);
        }
    }

    @Test
    void findAllArticles() throws Exception {
        List<ArticlesPlpDto> list = List.of(
                new ArticlesPlpDto("id","name", "img", "description"),
                new ArticlesPlpDto("id","name", "img", "description")
        );

        when(articlesService.findAllPlp()).thenReturn(list);

        mockMvc.perform(get("/api/articles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(list.size()))
                .andExpect(jsonPath("$[0].name").value("name"));

    }


    @Test
    void findById() throws Exception {
        String id = "id";
        Articles articles = new Articles(
                id, "name", "img", "description");

        when(articlesService.findById(id)).thenReturn(articles);
        mockMvc.perform(get("/api/articles/" + id))
                .andExpect(jsonPath("$.id", is(id)))
                .andExpect(status().isOk());
    }

    @Test
    void createArticles() throws Exception {
        Articles savedArticle = new Articles("id", "Name", "img", "description");

        when(articlesService.createArticles(any(ArticlesDto.class))).thenReturn(savedArticle);

        String request = """
    {
            "name": "Name",
         "imgUrl": "img",
         "description": "description",
         "localDateTime": "1221-01-12T00:00:00"
    }
    """;

        mockMvc.perform(post("/api/articles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("id")))
                .andExpect(jsonPath("$.name", is("Name")));
    }



    @Test
    void updateArticles() throws Exception{
        String id = "id1";
        String request = """
    {
            "name": "Name",
         "imgUrl": "img",
         "description": "description",
         "localDateTime": "1221-01-12T00:00:00"
    }
    """;
        Articles articles = new Articles(id, "Name3", "imageURL", "description");

        when(articlesService.updateArticles(Mockito.eq(id), Mockito.any())).thenReturn(articles);

        mockMvc.perform(put("/api/articles/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(jsonPath("$.name").value("Name3"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteArticles() throws Exception{
    String id = "id1";
        Mockito.doNothing().when(articlesService).deleteArticles(id);
        mockMvc.perform(delete("/api/articles/" + id))
            .andExpect(status().isOk());
    }
}