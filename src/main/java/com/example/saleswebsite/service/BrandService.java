package com.example.saleswebsite.service;

import com.example.saleswebsite.dto.BrandDTO;
import java.util.List;

public interface BrandService {
    List<BrandDTO> findAll();

    BrandDTO findById(Long id);

    BrandDTO create(BrandDTO dto);

    BrandDTO update(Long id, BrandDTO dto);

    void delete(Long id);

    BrandDTO findBySlug(String slug);
}
