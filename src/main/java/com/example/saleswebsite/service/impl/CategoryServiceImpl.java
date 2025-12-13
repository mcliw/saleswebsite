package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.dto.CategoryDTO;
import com.example.saleswebsite.entity.Category;
import com.example.saleswebsite.repository.CategoryRepository;
import com.example.saleswebsite.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.saleswebsite.exception.ApiException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

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
    @Transactional
    public CategoryDTO create(CategoryDTO dto) {
        log.info("Creating category {}", dto.getName());
        Category e = dto.toEntity();
        Category saved = categoryRepository.save(e);
        return CategoryDTO.fromEntity(saved);
    }

    @Override
    @Transactional
    public CategoryDTO update(Long id, CategoryDTO dto) {
        return categoryRepository.findById(id).map(existing -> {
            log.info("Updating category {}", id);
            Category updated = dto.toEntity();
            updated.setId(id);
            Category saved = categoryRepository.save(updated);
            return CategoryDTO.fromEntity(saved);
        }).orElseThrow(() -> new ApiException("Category not found"));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting category {}", id);
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryDTO findBySlug(String slug) {
        return categoryRepository.findBySlug(slug) == null ? null
                : CategoryDTO.fromEntity(categoryRepository.findBySlug(slug));
    }
}
