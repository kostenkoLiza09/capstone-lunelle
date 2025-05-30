package org.example.backend.model.record;

import org.example.backend.model.enums.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "perfumes")
public record Perfume(
        @Id
        String id,
        String name,
        String imageURL,
        String description,
        List<PerfumeVariant> variants,
        Selection selection,
        Brand brand,
        PerfumeFamily perfumeFamily,
        List<Season> seasons,
        List<Notes> notes
) {
}
