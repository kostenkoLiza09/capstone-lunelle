package org.example.backend.controller;

import org.example.backend.model.record.CartItem;
import org.example.backend.service.CartService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CartController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CartService cartService;

    @TestConfiguration
    static class Config {
        @Bean
        public CartService cartService() {
            return Mockito.mock(CartService.class);
        }
    }

    @Test
    void addToCart() throws Exception {
        CartItem mockItem = new CartItem();
        mockItem.setId("cartItemId");
        mockItem.setUserId("user1");
        mockItem.setProductId("prod1");
        mockItem.setQuantity(2);

        when(cartService.addToCart(any(CartItem.class))).thenReturn(mockItem);

        String json = """
                {
                    "userId": "user1",
                    "productId": "prod1",
                    "quantity": 2
                }
                """;

        mockMvc.perform(post("/api/cart/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("cartItemId"))
                .andExpect(jsonPath("$.userId").value("user1"))
                .andExpect(jsonPath("$.quantity").value(2));
    }

    @Test
    void getCart() throws Exception {
        CartItem mockItem = new CartItem();
        mockItem.setId("cartItemId");
        mockItem.setUserId("user1");
        mockItem.setProductId("prod1");
        mockItem.setQuantity(2);

        when(cartService.getCart(anyString())).thenReturn(List.of(mockItem));

        mockMvc.perform(get("/api/cart/user1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value("cartItemId"));
    }

    @Test
    void deleteCartItem() throws Exception {
        Mockito.doNothing().when(cartService).deleteCartItem(anyString());

        mockMvc.perform(delete("/api/cart/cartItemId"))
                .andExpect(status().isOk());
    }

    @Test
    void clearCart() throws Exception {
        Mockito.doNothing().when(cartService).clearCart(anyString());

        mockMvc.perform(delete("/api/cart/user/user1"))
                .andExpect(status().isOk());
    }
}
