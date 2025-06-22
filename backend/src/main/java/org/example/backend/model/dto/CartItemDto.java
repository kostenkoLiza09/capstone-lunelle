package org.example.backend.model.dto;

public record CartItemDto (
        String id,
        String userId,
        String productId,
        int quantity
){
}
