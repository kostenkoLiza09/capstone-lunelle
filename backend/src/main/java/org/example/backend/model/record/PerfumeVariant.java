package org.example.backend.model.record;

import org.example.backend.model.enums.Volume;

public record PerfumeVariant (
        Volume volume,
        float price
) {
}
