package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.dto.ProductVariantDTO;
import com.example.saleswebsite.entity.ProductVariant;
import com.example.saleswebsite.repository.ProductVariantRepository;
import com.example.saleswebsite.service.ProductVariantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.saleswebsite.exception.ApiException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductVariantServiceImpl implements ProductVariantService {
    private static final Logger log = LoggerFactory.getLogger(ProductVariantServiceImpl.class);

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
    @Transactional
    public ProductVariantDTO create(ProductVariantDTO dto) {
        log.info("Creating product variant {}", dto.getName());
        ProductVariant saved = repository.save(dto.toEntity());
        return ProductVariantDTO.fromEntity(saved);
    }

    @Override
    @Transactional
    public ProductVariantDTO update(Long id, ProductVariantDTO dto) {
        return repository.findById(id).map(existing -> {
            log.info("Updating product variant {}", id);
            ProductVariant updated = dto.toEntity();
            updated.setId(id);
            ProductVariant saved = repository.save(updated);
            return ProductVariantDTO.fromEntity(saved);
        }).orElseThrow(() -> new ApiException("ProductVariant not found"));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting product variant {}", id);
        repository.deleteById(id);
    }

    @Override
    public List<ProductVariantDTO> findByProductId(Long productId) {
        return repository.findByProductId(productId).stream().map(ProductVariantDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
