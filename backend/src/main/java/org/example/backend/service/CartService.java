package org.example.backend.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.model.record.CartItem;
import org.example.backend.repository.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartItemRepository cartItemRepo;

    public CartItem addToCart(CartItem cartItem) {
        if (cartItem.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        Optional<CartItem> existingItem = cartItemRepo.findByUserIdAndProductId(cartItem.getUserId(), cartItem.getProductId());
        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + cartItem.getQuantity());
            return cartItemRepo.save(item);
        } else {
            return cartItemRepo.save(cartItem);
        }
    }


    public List<CartItem> getCart(String userId) {
        return cartItemRepo.findByUserId(userId);
    }

    public void deleteCartItem(String cartItemId) {
        cartItemRepo.deleteById(cartItemId);
    }

    public void clearCart(String userId) {
        cartItemRepo.deleteByUserId(userId);
    }

}
