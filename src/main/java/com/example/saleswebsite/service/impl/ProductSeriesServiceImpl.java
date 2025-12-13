package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.dto.ProductSeriesDTO;
import com.example.saleswebsite.entity.ProductSeries;
import com.example.saleswebsite.repository.ProductSeriesRepository;
import com.example.saleswebsite.service.ProductSeriesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.saleswebsite.exception.ApiException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductSeriesServiceImpl implements ProductSeriesService {
    private static final Logger log = LoggerFactory.getLogger(ProductSeriesServiceImpl.class);

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
    @Transactional
    public ProductSeriesDTO create(ProductSeriesDTO dto) {
        log.info("Creating product series {}", dto.getName());
        ProductSeries saved = repository.save(dto.toEntity());
        return ProductSeriesDTO.fromEntity(saved);
    }

    @Override
    @Transactional
    public ProductSeriesDTO update(Long id, ProductSeriesDTO dto) {
        return repository.findById(id).map(existing -> {
            log.info("Updating product series {}", id);
            ProductSeries updated = dto.toEntity();
            updated.setId(id);
            ProductSeries saved = repository.save(updated);
            return ProductSeriesDTO.fromEntity(saved);
        }).orElseThrow(() -> new ApiException("ProductSeries not found"));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting product series {}", id);
        repository.deleteById(id);
    }

    @Override
    public ProductSeriesDTO findByCode(String code) {
        return repository.findBySlug(code) == null ? null : ProductSeriesDTO.fromEntity(repository.findBySlug(code));
    }
}
