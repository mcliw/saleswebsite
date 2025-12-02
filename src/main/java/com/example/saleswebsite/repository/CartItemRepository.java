package com.example.saleswebsite.repository;

import com.example.saleswebsite.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByProductVariantId(Long productVariantId);
}
