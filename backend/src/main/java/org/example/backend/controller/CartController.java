package org.example.backend.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.model.dto.CartItemDto;
import org.example.backend.model.record.CartItem;

import org.example.backend.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<CartItemDto> addToCart(@RequestBody CartItemDto cartItemDto) {
        CartItem cartItem = new CartItem(
                cartItemDto.id(),
                cartItemDto.userId(),
                cartItemDto.productId(),
                cartItemDto.quantity(),
                0,
                0,
                null,
                null
        );

        CartItem saved = cartService.addToCart(cartItem);

        CartItemDto responseDto = new CartItemDto(
                saved.getId(),
                saved.getUserId(),
                saved.getProductId(),
                saved.getQuantity()
        );

        return ResponseEntity.ok(responseDto);
    }



    @GetMapping("/{userId}")
    public List<CartItem> getCart(@PathVariable String userId) {
        return cartService.getCart(userId);
    }

    @DeleteMapping("/{cartItemId}")
    public void deleteCartItem(@PathVariable String cartItemId) {
        cartService.deleteCartItem(cartItemId);
    }

    @DeleteMapping("/user/{userId}")
    public void clearCart(@PathVariable String userId) {
        cartService.clearCart(userId);
    }
}