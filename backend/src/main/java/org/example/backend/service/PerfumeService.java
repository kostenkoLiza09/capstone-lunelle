package org.example.backend.service;

import org.example.backend.model.dto.PerfumeDto;
import org.example.backend.model.record.Perfume;
import org.example.backend.repository.PerfumeRepository;
import org.springframework.stereotype.Service;

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
}
