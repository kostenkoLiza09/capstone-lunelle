package org.example.backend.repository;

import org.example.backend.model.record.CartItem;
import org.example.backend.model.record.Perfume;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends MongoRepository<CartItem, String> {
    List<CartItem> findByUserId(String userId);
    void deleteByUserId(String userId);

    Optional<CartItem> findByUserIdAndProductId(String userId, String productId);


}

