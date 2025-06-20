package org.example.backend.model.dto;

import java.time.LocalDateTime;

public record ArticlesDto (String name,
                           String imgUrl,
                           String description,
                           LocalDateTime localDateTime) {
}
