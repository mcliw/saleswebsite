package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.dto.ProductAttributeValueDTO;
import com.example.saleswebsite.entity.ProductAttributeValue;
import com.example.saleswebsite.repository.ProductAttributeValueRepository;
import com.example.saleswebsite.service.ProductAttributeValueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.saleswebsite.exception.ApiException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductAttributeValueServiceImpl implements ProductAttributeValueService {
    private static final Logger log = LoggerFactory.getLogger(ProductAttributeValueServiceImpl.class);

    private final ProductAttributeValueRepository repository;

    public ProductAttributeValueServiceImpl(ProductAttributeValueRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ProductAttributeValueDTO> findAll() {
        return repository.findAll().stream().map(ProductAttributeValueDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public ProductAttributeValueDTO findById(Long id) {
        return repository.findById(id).map(ProductAttributeValueDTO::fromEntity).orElse(null);
    }

    @Override
    @Transactional
    public ProductAttributeValueDTO create(ProductAttributeValueDTO dto) {
        log.info("Creating product attribute value for attribute {}", dto.getAttributeId());
        ProductAttributeValue saved = repository.save(dto.toEntity());
        return ProductAttributeValueDTO.fromEntity(saved);
    }

    @Override
    @Transactional
    public ProductAttributeValueDTO update(Long id, ProductAttributeValueDTO dto) {
        return repository.findById(id).map(existing -> {
            log.info("Updating product attribute value {}", id);
            ProductAttributeValue updated = dto.toEntity();
            updated.setId(id);
            ProductAttributeValue saved = repository.save(updated);
            return ProductAttributeValueDTO.fromEntity(saved);
        }).orElseThrow(() -> new ApiException("ProductAttributeValue not found"));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting product attribute value {}", id);
        repository.deleteById(id);
    }

    @Override
    public List<ProductAttributeValueDTO> findByProductId(Long productId) {
        return repository.findByProductId(productId).stream().map(ProductAttributeValueDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
