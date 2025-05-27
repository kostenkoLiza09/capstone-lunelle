package org.example.backend.model.dto;

import org.example.backend.model.enums.*;
import org.example.backend.model.record.PerfumeVariant;

import java.util.List;

public record ParfumeDto (String Id,
                         String name,
                         String imageURL,
                         String description,
                         List<PerfumeVariant> variants,
                         Selection selection,
                         Brand brand,
                         PerfumeFamily Parfumefamily,
                         List<Season> seasons,
                         List<Notes> notes){
}
