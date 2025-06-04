package org.example.backend.controller;

import org.example.backend.model.enums.*;
import org.example.backend.model.plp.PerfumePlpDto;
import org.example.backend.model.record.Perfume;
import org.example.backend.model.record.PerfumeVariant;
import org.example.backend.service.PerfumeService;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.is;


@WebMvcTest(PerfumeController.class)
class PerfumeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PerfumeService perfumeService;

    @TestConfiguration
    static class Config {
        @Bean
        public PerfumeService perfumeService() {
            return Mockito.mock(PerfumeService.class);
        }
    }

    @Test
    void findAll() throws Exception {

        List<PerfumePlpDto> perfumes = List.of(
                new PerfumePlpDto("id1", "name1", "imageURL1", 49.99f, Volume.ML30),
                new PerfumePlpDto("id2", "name2", "imageURL2", 59.99f, Volume.ML20)
    );

        when(perfumeService.findAllPlp()).thenReturn(perfumes);
        mockMvc.perform(get("/api/perfumes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("name1")))
                .andExpect(jsonPath("$[1].price", is(59.99)));
    }

    @Test
    void addRecipe() throws Exception{

        String request = """
                {
                       "name": "Name",
                       "imageURL": "imageURL",
                       "description": "description",
                       "variants": [
                         {"volume": "ML30", "price": 49.99},
                         {"volume": "ML50", "price": 59.99}
                       ],
                       "selection": "WOMEN",
                       "brand": "ARMANI",
                       "perfumeFamily": "AROMATIC",
                       "seasons": ["SPRING", "SUMMER"],
                       "notes": ["AQUATIC", "VANILLA"]
                     }
                """;
        Perfume createdPerfume = new Perfume(
                "generated-id-123",
                "Name",
                "imageURL",
                "description",
                List.of(
                        new PerfumeVariant(Volume.ML30, 49.99f),
                        new PerfumeVariant(Volume.ML50, 59.99f)
                ),
                Selection.WOMEN,
                Brand.ARMANI,
                PerfumeFamily.AROMATIC,
                List.of(Season.SPRING, Season.SUMMER),
                List.of(Notes.AQUATIC, Notes.VANILLA)
        );

        when(perfumeService.addPerfume(Mockito.any())).thenReturn(createdPerfume);

        mockMvc.perform(post("/api/add")
                        .contentType("application/json")
                        .content(request))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty());
    }

    @Test
    void updatePerfume() throws Exception{
        String id = "id1";
        String request = """
                {
                       "name": "Name",
                       "imageURL": "imageURL",
                       "description": "description",
                       "variants": [
                         {"volume": "ML30", "price": 49.99},
                         {"volume": "ML50", "price": 59.99}
                       ],
                       "selection": "WOMEN",
                       "brand": "ARMANI",
                       "perfumeFamily": "AROMATIC",
                       "seasons": ["SPRING", "SUMMER"],
                       "notes": ["AQUATIC", "VANILLA"]
                     }
                """;

        Perfume perfume = new Perfume(id, "Name3", "imageURL", "description",
                List.of(new PerfumeVariant(Volume.ML30, 49.99f), new PerfumeVariant(Volume.ML50, 59.99f)),
                Selection.WOMEN, Brand.ARMANI, PerfumeFamily.AROMATIC, List.of(Season.SPRING, Season.SUMMER),
                List.of(Notes.AQUATIC, Notes.VANILLA));

        when(perfumeService.updatePerfume(Mockito.eq(id), Mockito.any())).thenReturn(perfume);

        mockMvc.perform(put("/api/update/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(jsonPath("$.name").value("Name3"))
                .andExpect(status().isOk());
    }

    @Test
    void deletePerfume() throws Exception {
        String id = "id1";
        Mockito.doNothing().when(perfumeService).deletePerfume(id);
        mockMvc.perform(delete("/api/delete/" + id))
                .andExpect(status().isOk());
    }

    @Test
    void findById () throws Exception {
        String id = "id1";
        Perfume perfume = new Perfume(id, "Name3", "imageURL", "description",
                List.of(new PerfumeVariant(Volume.ML30, 49.99f), new PerfumeVariant(Volume.ML50, 59.99f)),
                Selection.WOMEN, Brand.ARMANI, PerfumeFamily.AROMATIC, List.of(Season.SPRING, Season.SUMMER),
                List.of(Notes.AQUATIC, Notes.VANILLA));
        when(perfumeService.findById(id)).thenReturn(perfume);

        mockMvc.perform(get("/api/find/" + id))
                .andExpect(jsonPath("$.id", is(id)))
                .andExpect(status().isOk());

    }
    @Test
    void getPerfumesBySelection() throws Exception {
        String selection = "WOMEN";

        List<PerfumePlpDto> perfumePlpDtos = List.of(
                new PerfumePlpDto("id1", "Name1", "img1", 30, Volume.ML20),
                new PerfumePlpDto("id2", "Name2", "img2", 50, Volume.ML20)
        );

        when(perfumeService.filterBySelection(selection)).thenReturn(perfumePlpDtos);

        mockMvc.perform(get("/api/perfumes/selection")
                        .param("selection", selection))
                .andExpect(status().isOk())
                .andExpect(status().isOk());
    }


}