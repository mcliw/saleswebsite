package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.dto.ProductSeriesDTO;
import com.example.saleswebsite.entity.ProductSeries;
import com.example.saleswebsite.repository.ProductSeriesRepository;
import com.example.saleswebsite.service.ProductSeriesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductSeriesServiceImpl implements ProductSeriesService {

    private final ProductSeriesRepository repository;

    public ProductSeriesServiceImpl(ProductSeriesRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ProductSeriesDTO> findAll() {
        return repository.findAll().stream().map(ProductSeriesDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public ProductSeriesDTO findById(Long id) {
        return repository.findById(id).map(ProductSeriesDTO::fromEntity).orElse(null);
    }

    @Override
    public ProductSeriesDTO create(ProductSeriesDTO dto) {
        ProductSeries saved = repository.save(dto.toEntity());
        return ProductSeriesDTO.fromEntity(saved);
    }

    @Override
    public ProductSeriesDTO update(Long id, ProductSeriesDTO dto) {
        return repository.findById(id).map(existing -> {
            ProductSeries updated = dto.toEntity();
            updated.setId(id);
            ProductSeries saved = repository.save(updated);
            return ProductSeriesDTO.fromEntity(saved);
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public ProductSeriesDTO findByCode(String code) {
        return repository.findBySlug(code) == null ? null : ProductSeriesDTO.fromEntity(repository.findBySlug(code));
    }
}
