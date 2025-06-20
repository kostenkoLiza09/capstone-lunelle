package org.example.backend.controller;

import org.example.backend.model.dto.PerfumeDto;
import org.example.backend.model.plp.PerfumePlpDto;
import org.example.backend.model.record.Perfume;
import org.example.backend.service.PerfumeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api")
public class PerfumeController {
    private final PerfumeService perfumeService;

    public PerfumeController(PerfumeService perfumeService) {
        this.perfumeService = perfumeService;
    }
    @GetMapping("/perfumes")
    public List<PerfumePlpDto> findAll() {
        return perfumeService.findAllPlpFiltered(null, null, null, null, null, null);
    }

    @GetMapping("/perfumes/selection")
    public List<PerfumePlpDto> getPerfumesBySelection(@RequestParam String selection) {
        return perfumeService.filterBySelection(selection);
    }

    @PostMapping("/add")
    public Perfume addRecipe(@RequestBody PerfumeDto perfumeDto) {
        return perfumeService.addPerfume(perfumeDto);
    }

    @PutMapping("/update/{id}")
    public Perfume updatePerfume(@PathVariable String id, @RequestBody PerfumeDto perfumeDto) {
        return perfumeService.updatePerfume(id, perfumeDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePerfume(@PathVariable String id) {
        perfumeService.deletePerfume(id);
    }

    @GetMapping("/{id}")
    public Perfume findById(@PathVariable String id) {
        return perfumeService.findById(id);
    }
}
