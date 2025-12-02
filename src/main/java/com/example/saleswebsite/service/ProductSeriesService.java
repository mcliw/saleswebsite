package com.example.saleswebsite.service;

import com.example.saleswebsite.dto.ProductSeriesDTO;

import java.util.List;

public interface ProductSeriesService {
    List<ProductSeriesDTO> findAll();

    ProductSeriesDTO findById(Long id);

    ProductSeriesDTO create(ProductSeriesDTO dto);

    ProductSeriesDTO update(Long id, ProductSeriesDTO dto);

    void delete(Long id);

    ProductSeriesDTO findByCode(String code);
}
