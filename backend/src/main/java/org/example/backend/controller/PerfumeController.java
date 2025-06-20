package org.example.backend.controller;

import org.example.backend.config.PerfumeIndex;
import org.example.backend.config.PerfumeIndexCronService;
import org.example.backend.config.PerfumeIndexRepository;
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
    private static final Logger logger = LoggerFactory.getLogger(PerfumeController.class);
    private final PerfumeService perfumeService;
    private final PerfumeIndexRepository perfumeIndexRepository;
    private final PerfumeIndexCronService perfumeIndexCronService;

    public PerfumeController(PerfumeService perfumeService, PerfumeIndexRepository perfumeIndexRepository, PerfumeIndexCronService perfumeIndexCronService) {
        this.perfumeService = perfumeService;
        this.perfumeIndexRepository = perfumeIndexRepository;
        this.perfumeIndexCronService = perfumeIndexCronService;
    }

    @GetMapping("/perfumes")
    public List<PerfumePlpDto> findFiltered(
            @RequestParam(required = false) String selection,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String volume,
            @RequestParam(required = false) String perfumeFamily,
            @RequestParam(required = false) String seasons,
            @RequestParam(required = false) String notes
    ) {
        return perfumeService.findAllPlpFiltered(selection, brand, volume, perfumeFamily, seasons, notes);
    }
    @GetMapping("/search")
    public List<PerfumeIndex> search(@RequestParam("q") String query) {
        logger.info("Search query: {}", query);
        List<PerfumeIndex> results = perfumeIndexRepository.findByPerfumeInfoContainingIgnoreCase(query);
        logger.info("Results found: {}", results.size());
        return results;
    }
    @GetMapping("/sync")
    public String manualSync() {
        perfumeIndexCronService.syncToElastic();
        return "Sync started";
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
