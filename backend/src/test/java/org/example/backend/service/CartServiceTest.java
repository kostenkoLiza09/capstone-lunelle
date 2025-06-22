package org.example.backend.service;

import org.example.backend.model.record.CartItem;
import org.example.backend.repository.CartItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CartServiceTest {

    private CartItemRepository cartItemRepository;
    private CartService cartService;

    @BeforeEach
    void setUp() {
        cartItemRepository = mock(CartItemRepository.class);
        cartService = new CartService(cartItemRepository);
    }

    @Test
    void addToCart_NewItem_SavesItem() {
        CartItem newItem = CartItem.builder()
                .id(null)
                .userId("user1")
                .productId("product1")
                .quantity(2)
                .price(15.0)
                .variantVolume(50)
                .perfumeName("Perfume A")
                .imageURL("imageA.jpg")
                .build();

        when(cartItemRepository.findByUserIdAndProductId("user1", "product1"))
                .thenReturn(Optional.empty());
        when(cartItemRepository.save(newItem)).thenReturn(newItem);

        CartItem result = cartService.addToCart(newItem);

        assertEquals(newItem, result);
        verify(cartItemRepository).save(newItem);
    }

    @Test
    void addToCart_ExistingItem_UpdatesQuantity() {
        CartItem existingItem = CartItem.builder()
                .id("cart123")
                .userId("user1")
                .productId("product1")
                .quantity(3)
                .price(15.0)
                .variantVolume(50)
                .perfumeName("Perfume A")
                .imageURL("imageA.jpg")
                .build();

        CartItem toAdd = CartItem.builder()
                .userId("user1")
                .productId("product1")
                .quantity(2)
                .price(15.0)
                .variantVolume(50)
                .perfumeName("Perfume A")
                .imageURL("imageA.jpg")
                .build();

        when(cartItemRepository.findByUserIdAndProductId("user1", "product1"))
                .thenReturn(Optional.of(existingItem));
        when(cartItemRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        CartItem result = cartService.addToCart(toAdd);

        assertEquals(5, result.getQuantity()); // 3 + 2 = 5
        verify(cartItemRepository).save(existingItem);
    }

    @Test
    void addToCart_QuantityZero_ThrowsException() {
        CartItem item = CartItem.builder()
                .userId("user1")
                .productId("product1")
                .quantity(0)
                .price(15.0)
                .variantVolume(50)
                .perfumeName("Perfume A")
                .imageURL("imageA.jpg")
                .build();

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            cartService.addToCart(item);
        });

        assertEquals("Quantity must be greater than zero", ex.getMessage());
    }

    @Test
    void getCart_ReturnsList() {
        CartItem item1 = CartItem.builder()
                .id("1")
                .userId("user1")
                .productId("product1")
                .quantity(2)
                .price(15.0)
                .variantVolume(50)
                .perfumeName("Perfume A")
                .imageURL("imageA.jpg")
                .build();

        CartItem item2 = CartItem.builder()
                .id("2")
                .userId("user1")
                .productId("product2")
                .quantity(1)
                .price(20.0)
                .variantVolume(100)
                .perfumeName("Perfume B")
                .imageURL("imageB.jpg")
                .build();

        when(cartItemRepository.findByUserId("user1")).thenReturn(List.of(item1, item2));

        List<CartItem> cart = cartService.getCart("user1");

        assertEquals(2, cart.size());
        assertTrue(cart.contains(item1));
        assertTrue(cart.contains(item2));
    }

    @Test
    void deleteCartItem_VerifyDeleteCalled() {
        cartService.deleteCartItem("cartItemId123");

        verify(cartItemRepository).deleteById("cartItemId123");
    }

    @Test
    void clearCart_VerifyDeleteByUserIdCalled() {
        cartService.clearCart("user1");

        verify(cartItemRepository).deleteByUserId("user1");
    }
}
