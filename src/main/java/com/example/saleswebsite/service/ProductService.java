package com.example.saleswebsite.service;

import com.example.saleswebsite.dto.ProductDTO;
import java.util.List;

public interface ProductService {
    List<ProductDTO> findAll();

    ProductDTO findById(Long id);

    ProductDTO create(ProductDTO dto);

    ProductDTO update(Long id, ProductDTO dto);

    void delete(Long id);

    List<ProductDTO> findByName(String name);
}
