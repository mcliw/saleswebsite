package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.dto.CategoryDTO;
import com.example.saleswebsite.entity.Category;
import com.example.saleswebsite.repository.CategoryRepository;
import com.example.saleswebsite.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll().stream().map(CategoryDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findById(Long id) {
        return categoryRepository.findById(id).map(CategoryDTO::fromEntity).orElse(null);
    }

    @Override
    public CategoryDTO create(CategoryDTO dto) {
        Category e = dto.toEntity();
        Category saved = categoryRepository.save(e);
        return CategoryDTO.fromEntity(saved);
    }

    @Override
    public CategoryDTO update(Long id, CategoryDTO dto) {
        return categoryRepository.findById(id).map(existing -> {
            Category updated = dto.toEntity();
            updated.setId(id);
            Category saved = categoryRepository.save(updated);
            return CategoryDTO.fromEntity(saved);
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryDTO findBySlug(String slug) {
        return categoryRepository.findBySlug(slug) == null ? null
                : CategoryDTO.fromEntity(categoryRepository.findBySlug(slug));
    }
}
