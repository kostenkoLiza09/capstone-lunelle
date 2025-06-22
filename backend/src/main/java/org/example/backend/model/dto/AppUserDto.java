package org.example.backend.model.dto;

public record AppUserDto (
        String username,
        String avatarUrl,
        String firstName,
        String lastName,
        String city,
        String address,
        String phoneNumber){
}
