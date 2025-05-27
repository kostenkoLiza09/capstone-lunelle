package org.example.backend.service;

import org.example.backend.exception.PerfumeNotFoundException;
import org.example.backend.model.dto.PerfumeDto;
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

    public List<Perfume> findAll(){
        return perfumeRepository.findAll();
    }
}
