package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.dto.ProductAttributeValueDTO;
import com.example.saleswebsite.entity.ProductAttributeValue;
import com.example.saleswebsite.repository.ProductAttributeValueRepository;
import com.example.saleswebsite.service.ProductAttributeValueService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductAttributeValueServiceImpl implements ProductAttributeValueService {

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
    public ProductAttributeValueDTO create(ProductAttributeValueDTO dto) {
        ProductAttributeValue saved = repository.save(dto.toEntity());
        return ProductAttributeValueDTO.fromEntity(saved);
    }

    @Override
    public ProductAttributeValueDTO update(Long id, ProductAttributeValueDTO dto) {
        return repository.findById(id).map(existing -> {
            ProductAttributeValue updated = dto.toEntity();
            updated.setId(id);
            ProductAttributeValue saved = repository.save(updated);
            return ProductAttributeValueDTO.fromEntity(saved);
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<ProductAttributeValueDTO> findByProductId(Long productId) {
        return repository.findByProductId(productId).stream().map(ProductAttributeValueDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
