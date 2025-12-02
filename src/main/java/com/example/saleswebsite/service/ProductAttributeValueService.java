package com.example.saleswebsite.service;

import com.example.saleswebsite.dto.ProductAttributeValueDTO;

import java.util.List;

public interface ProductAttributeValueService {
    List<ProductAttributeValueDTO> findAll();

    ProductAttributeValueDTO findById(Long id);

    ProductAttributeValueDTO create(ProductAttributeValueDTO dto);

    ProductAttributeValueDTO update(Long id, ProductAttributeValueDTO dto);

    void delete(Long id);

    List<ProductAttributeValueDTO> findByProductId(Long productId);
}
