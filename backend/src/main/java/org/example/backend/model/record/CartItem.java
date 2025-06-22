package org.example.backend.model.record;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("cart")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {
    @Id
    private String id;
    private String userId;
    private String productId;
    private int quantity;
    double price;
    int variantVolume;
    String perfumeName;
    String imageURL;
}
