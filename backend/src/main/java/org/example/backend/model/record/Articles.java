package org.example.backend.model.record;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "articles")
public record Articles (String id,
                        String name,
                        String imgUrl,
                        String description,
                        LocalDateTime localDateTime
) {
}
