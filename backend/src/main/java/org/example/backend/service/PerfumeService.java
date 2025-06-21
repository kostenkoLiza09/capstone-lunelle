package org.example.backend.service;

import org.example.backend.exception.PerfumeNotFoundException;
import org.example.backend.model.dto.PerfumeDto;
import org.example.backend.model.enums.*;
import org.example.backend.model.plp.PerfumePlpDto;
import org.example.backend.model.record.Perfume;
import org.example.backend.repository.PerfumeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfumeService {

    private final PerfumeRepository perfumeRepository;

    public PerfumeService(PerfumeRepository perfumeRepository) {
        this.perfumeRepository = perfumeRepository;
    }
    public List<PerfumePlpDto> findAllPlpFiltered(String selection, String brand, String volume,
                                                  String perfumeFamily, String seasons, String notes) {
        List<String> selectionList = splitParam(selection);
        List<String> brandList = splitParam(brand);
        List<String> volumeList = splitParam(volume);
        List<String> perfumeFamilyList = splitParam(perfumeFamily);
        List<String> seasonsList = splitParam(seasons);
        List<String> notesList = splitParam(notes);

        return perfumeRepository.findAll().stream()
                .filter(p -> selection(p, selectionList))
                .filter(p -> brand(p, brandList))
                .filter(p -> volume(p, volumeList))
                .filter(p -> perfumeFamily(p, perfumeFamilyList))
                .filter(p -> seasons(p, seasonsList))
                .filter(p -> notes(p, notesList))
                .map(PerfumePlpDto::new)
                .toList();
    }

    private static final Logger logger = LoggerFactory.getLogger(PerfumeService.class);

    private List<String> splitParam(String param) {
        if (param == null || param.isEmpty()) return List.of();
        return List.of(param.toUpperCase().split(","));
    }

    private boolean brand(Perfume p, List<String> brands) {
        if (brands == null || brands.isEmpty()) return true;

        for (String brandStr : brands) {
            try {
                Brand brandEnum = Brand.valueOf(brandStr.toUpperCase());
                if (p.brand().equals(brandEnum)) {
                    return true;
                }
            } catch (IllegalArgumentException e) {
                logger.warn("Invalid brand value '{}' ignored.", brandStr);
            }
        }
        return false;
    }




    private boolean selection(Perfume p, List<String> selections) {
        if (selections == null || selections.isEmpty()) return true;
        for (String sel : selections) {
            try {
                Selection selEnum = Selection.valueOf(sel.toUpperCase());
                if (p.selection().equals(selEnum)) return true;
            } catch (IllegalArgumentException e) {
                logger.warn("Invalid selection value '{}' ignored.", sel);
            }
        }
        return false;
    }

    private boolean volume(Perfume p, List<String> volumes) {
        if (volumes == null || volumes.isEmpty()) return true;
        for (String volumeStr : volumes) {
            try {
                Volume volumeEnum = Volume.valueOf(volumeStr.toUpperCase());
                if (p.variants().stream().anyMatch(v -> v.volume().equals(volumeEnum))) {
                    return true;
                }
            } catch (IllegalArgumentException e) {
                logger.warn("Invalid volume value '{}' ignored.", volumeStr);
            }
        }
        return false;
    }

    private boolean perfumeFamily(Perfume p, List<String> perfumeFamilies) {
        if (perfumeFamilies == null || perfumeFamilies.isEmpty()) return true;

        for (String familyStr : perfumeFamilies) {
            try {
                PerfumeFamily familyEnum = PerfumeFamily.valueOf(familyStr.toUpperCase());
                if (p.perfumeFamily().equals(familyEnum)) {
                    return true;
                }
            } catch (IllegalArgumentException e) {
                logger.warn("Invalid perfumeFamily value '{}' ignored.", familyStr);
            }
        }
        return false;
    }



    private boolean seasons(Perfume p, List<String> seasonsList) {
        if (seasonsList == null || seasonsList.isEmpty()) return true;

        for (String seasonStr : seasonsList) {
            try {
                Season seasonEnum = Season.valueOf(seasonStr.toUpperCase());
                if (p.seasons().stream().anyMatch(s -> s.equals(seasonEnum))) {
                    return true;
                }
            } catch (IllegalArgumentException e) {
                logger.warn("Invalid season value '{}' ignored.", seasonStr);
            }
        }
        return false;
    }

    private boolean notes(Perfume p, List<String> notesList) {
        if (notesList == null || notesList.isEmpty()) return true;

        for (String noteStr : notesList) {
            try {
                Notes noteEnum = Notes.valueOf(noteStr.toUpperCase());
                if (p.notes().stream().anyMatch(n -> n.equals(noteEnum))) {
                    return true;
                }
            } catch (IllegalArgumentException e) {
                logger.warn("Invalid note value '{}' ignored.", noteStr);
            }
        }
        return false;
    }

    public List<PerfumePlpDto> filterBySelection(String category) {
        List<Perfume> perfumes = switch (category.toUpperCase()) {
            case "WOMEN" -> perfumeRepository.findBySelection(Selection.WOMEN);
            case "MEN" -> perfumeRepository.findBySelection(Selection.MEN);
            case "UNISEX" -> perfumeRepository.findBySelection(Selection.UNISEX);
            default -> throw new IllegalArgumentException("Unknown selection: " + category);
        };

        return perfumes.stream().map(PerfumePlpDto::new).toList();
    }



    public Perfume addPerfume(PerfumeDto perfumeDto){
        Perfume perfume = new Perfume(
                null,
                perfumeDto.name(),
                perfumeDto.imageURL(),
                perfumeDto.description(),
                perfumeDto.variants(),
                perfumeDto.selection(),
                perfumeDto.brand(),
                perfumeDto.perfumeFamily(),
                perfumeDto.seasons(),
                perfumeDto.notes()
        );
        return perfumeRepository.save(perfume);
    }

    public Perfume updatePerfume( String id,PerfumeDto perfumeDto){
        Perfume oldData = perfumeRepository.findById(id)
                .orElseThrow(() -> new PerfumeNotFoundException("Perfume is not found"));

        Perfume newData = new Perfume(
                oldData.id(),
                perfumeDto.name(),
                perfumeDto.imageURL(),
                perfumeDto.description(),
                perfumeDto.variants(),
                perfumeDto.selection(),
                perfumeDto.brand(),
                perfumeDto.perfumeFamily(),
                perfumeDto.seasons(),
                perfumeDto.notes()
        );
        return perfumeRepository.save(newData);
    }

    public void deletePerfume (String id){
        Perfume perfume = perfumeRepository.findById(id)
                .orElseThrow(() -> new PerfumeNotFoundException("Perfume with" + id + "is not found"));
        perfumeRepository.delete(perfume);
    }

    public Perfume findById(String id){
        return perfumeRepository.findById(id)
                .orElseThrow(() -> new PerfumeNotFoundException("Perfume with" + id + "is not found"));
    }

    public List<PerfumePlpDto> findAllPlp() {
        return perfumeRepository.findAll().stream()
                .map(PerfumePlpDto::new)
                .toList();
    }
}
