package org.example.backend.model.dto;

import org.example.backend.model.enums.*;
import org.example.backend.model.record.PerfumeVariant;

import java.util.List;

public record PerfumeDto (
                         String name,
                         String imageURL,
                         String description,
                         List<PerfumeVariant> variants,
                         Selection selection,
                         Brand brand,
                         PerfumeFamily perfumeFamily,
                         List<Season> seasons,
                         List<Notes> notes){
}
