package org.example.backend.model.record;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "articles")
public record Articles (
        @Id
        String id,
        String name,
        String imgUrl,
        String description
) {}

