package com.example.saleswebsite.repository;

import com.example.saleswebsite.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
    List<ProductVariant> findByProductId(Long productId);

    List<ProductVariant> findByProductSeriesId(Long productSeriesId);

    ProductVariant findByName(String name);
}
