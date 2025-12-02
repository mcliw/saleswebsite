package com.example.saleswebsite.service;

import com.example.saleswebsite.dto.ProductVariantDTO;

import java.util.List;

public interface ProductVariantService {
    List<ProductVariantDTO> findAll();

    ProductVariantDTO findById(Long id);

    ProductVariantDTO create(ProductVariantDTO dto);

    ProductVariantDTO update(Long id, ProductVariantDTO dto);

    void delete(Long id);

    List<ProductVariantDTO> findByProductId(Long productId);
}
