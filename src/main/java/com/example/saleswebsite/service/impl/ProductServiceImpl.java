package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.dto.ProductDTO;
import com.example.saleswebsite.entity.Product;
import com.example.saleswebsite.repository.ProductRepository;
import com.example.saleswebsite.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream().map(ProductDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public ProductDTO findById(Long id) {
        return productRepository.findById(id).map(ProductDTO::fromEntity).orElse(null);
    }

    @Override
    public ProductDTO create(ProductDTO dto) {
        Product e = dto.toEntity();
        Product saved = productRepository.save(e);
        return ProductDTO.fromEntity(saved);
    }

    @Override
    public ProductDTO update(Long id, ProductDTO dto) {
        return productRepository.findById(id).map(existing -> {
            Product updated = dto.toEntity();
            updated.setId(id);
            Product saved = productRepository.save(updated);
            return ProductDTO.fromEntity(saved);
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDTO> findByName(String name) {
        if (name == null)
            return List.of();
        return productRepository.findAll().stream()
                .filter(p -> p.getName() != null && p.getName().toLowerCase().contains(name.toLowerCase()))
                .map(ProductDTO::fromEntity).collect(Collectors.toList());
    }
}
