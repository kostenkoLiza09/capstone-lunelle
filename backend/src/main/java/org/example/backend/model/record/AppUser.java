package org.example.backend.model.record;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;




@Document("users")
public record AppUser(
        @Id
        String id,
        String username,
        String avatarUrl,
        String firstName,
        String lastName,
        String city,
        String address,
        String phoneNumber
) {}