package com.example.ecommerce.repository;

import com.example.ecommerce.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    // Custom query to find all cart items for a specific user ID
    List<CartItem> findByUserId(Long userId);

    // Custom query to find a specific product inside a specific user's cart
    Optional<CartItem> findByUserIdAndProductId(Long userId, Long productId);
}
