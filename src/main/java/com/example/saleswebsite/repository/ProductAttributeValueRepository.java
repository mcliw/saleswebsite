package com.example.saleswebsite.repository;

import com.example.saleswebsite.entity.ProductAttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductAttributeValueRepository extends JpaRepository<ProductAttributeValue, Long> {
    List<ProductAttributeValue> findByProductId(Long productId);

    List<ProductAttributeValue> findByAttributeId(Long attributeId);

    List<ProductAttributeValue> findByProductIdAndAttributeId(Long productId, Long attributeId);
}
