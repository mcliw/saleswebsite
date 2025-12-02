package com.example.saleswebsite.service;

import com.example.saleswebsite.dto.CategoryDTO;
import java.util.List;

public interface CategoryService {
    List<CategoryDTO> findAll();

    CategoryDTO findById(Long id);

    CategoryDTO create(CategoryDTO dto);

    CategoryDTO update(Long id, CategoryDTO dto);

    void delete(Long id);

    CategoryDTO findBySlug(String slug);
}
