package org.example.backend.controller;

import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<CartItem> addToCart(@RequestBody CartItem cartItem) {
        CartItem saved = cartService.addToCart(cartItem);
        return ResponseEntity.ok(saved);
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