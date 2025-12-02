package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.dto.ProductVariantDTO;
import com.example.saleswebsite.entity.ProductVariant;
import com.example.saleswebsite.repository.ProductVariantRepository;
import com.example.saleswebsite.service.ProductVariantService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductVariantServiceImpl implements ProductVariantService {

    private final ProductVariantRepository repository;

    public ProductVariantServiceImpl(ProductVariantRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ProductVariantDTO> findAll() {
        return repository.findAll().stream().map(ProductVariantDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public ProductVariantDTO findById(Long id) {
        return repository.findById(id).map(ProductVariantDTO::fromEntity).orElse(null);
    }

    @Override
    public ProductVariantDTO create(ProductVariantDTO dto) {
        ProductVariant saved = repository.save(dto.toEntity());
        return ProductVariantDTO.fromEntity(saved);
    }

    @Override
    public ProductVariantDTO update(Long id, ProductVariantDTO dto) {
        return repository.findById(id).map(existing -> {
            ProductVariant updated = dto.toEntity();
            updated.setId(id);
            ProductVariant saved = repository.save(updated);
            return ProductVariantDTO.fromEntity(saved);
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<ProductVariantDTO> findByProductId(Long productId) {
        return repository.findByProductId(productId).stream().map(ProductVariantDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
