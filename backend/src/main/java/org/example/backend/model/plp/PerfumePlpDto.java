package org.example.backend.model.plp;

import org.example.backend.model.enums.Volume;
import org.example.backend.model.record.Perfume;
import org.example.backend.model.record.PerfumeVariant;

public record PerfumePlpDto(
        String id,
        String name,
        String imageURL,
        float price,
        Volume volume
) {
    public PerfumePlpDto(Perfume perfume) {
        this(
                perfume.id(),
                perfume.name(),
                perfume.imageURL(),
                perfume.variants().stream()
                        .findFirst()
                        .map(PerfumeVariant::price)
                        .orElse(0f),
                perfume.variants().stream()
                        .findFirst()
                        .map(PerfumeVariant::volume)
                        .orElse(null)
        );
    }
}