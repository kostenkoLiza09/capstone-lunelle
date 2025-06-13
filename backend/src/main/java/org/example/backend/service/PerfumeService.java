package org.example.backend.service;

import org.example.backend.exception.PerfumeNotFoundException;
import org.example.backend.model.dto.PerfumeDto;
import org.example.backend.model.enums.*;
import org.example.backend.model.plp.PerfumePlpDto;
import org.example.backend.model.record.Perfume;
import org.example.backend.repository.PerfumeRepository;
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
        return perfumeRepository.findAll().stream()

                .filter(p -> selection(p, selection))
                .filter(p -> brand(p, brand))
                .filter(p -> volume(p, volume))
                .filter(p -> perfumeFamily(p, perfumeFamily))
                .filter(p -> seasons(p, seasons))
                .filter(p -> notes(p, notes))
                .map(PerfumePlpDto::new)
                .toList();
    }

    private boolean brand(Perfume p, String brand) {
        if (brand == null || brand.isEmpty()) return true;
        try {

            Brand brandEnum = Brand.valueOf(brand.toUpperCase());
            return p.brand().equals(brandEnum);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private boolean selection(Perfume p, String selection) {
        if (selection == null || selection.isEmpty()) return true;
        try {
            Selection selectionEnum = Selection.valueOf(selection.toUpperCase());
            return p.selection().equals(selectionEnum);
        } catch (IllegalArgumentException e) {
            return false;
        }

    }

    private boolean volume(Perfume p, String volume) {
        if (volume == null || volume.isEmpty()) return true;
        try {
            Volume volumeEnum = Volume.valueOf(volume.toUpperCase());
            return p.variants().stream()
                    .anyMatch(variant -> variant.volume().equals(volumeEnum));
        } catch (IllegalArgumentException e) {
            return false;
        }

    }


    private boolean perfumeFamily(Perfume p, String perfumeFamily) {
        if (perfumeFamily == null || perfumeFamily.isEmpty()) return true;
        try {
            PerfumeFamily familyEnum = PerfumeFamily.valueOf(perfumeFamily.toUpperCase());
            return p.perfumeFamily().equals(familyEnum);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }



    private boolean seasons(Perfume p, String seasons) {
        if (seasons == null || seasons.isEmpty()) return true;
        try {
            Season seasonEnum = Season.valueOf(seasons.toUpperCase());
            return p.seasons().stream().anyMatch(s -> s.equals(seasonEnum));
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private boolean notes(Perfume p, String notes) {
        if (notes == null || notes.isEmpty()) return true;
        try {
            Notes notesEnum = Notes.valueOf(notes.toUpperCase());
            return p.notes().stream().anyMatch(n -> n.equals(notesEnum));
        } catch (IllegalArgumentException e) {
            return false;
        }
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
