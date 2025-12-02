package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.dto.BrandDTO;
import com.example.saleswebsite.entity.Brand;
import com.example.saleswebsite.repository.BrandRepository;
import com.example.saleswebsite.service.BrandService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<BrandDTO> findAll() {
        return brandRepository.findAll().stream().map(BrandDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public BrandDTO findById(Long id) {
        return brandRepository.findById(id).map(BrandDTO::fromEntity).orElse(null);
    }

    @Override
    public BrandDTO create(BrandDTO dto) {
        Brand e = dto.toEntity();
        Brand saved = brandRepository.save(e);
        return BrandDTO.fromEntity(saved);
    }

    @Override
    public BrandDTO update(Long id, BrandDTO dto) {
        return brandRepository.findById(id).map(existing -> {
            Brand updated = dto.toEntity();
            updated.setId(id);
            Brand saved = brandRepository.save(updated);
            return BrandDTO.fromEntity(saved);
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        brandRepository.deleteById(id);
    }

    @Override
    public BrandDTO findBySlug(String slug) {
        return brandRepository.findBySlug(slug) == null ? null : BrandDTO.fromEntity(brandRepository.findBySlug(slug));
    }
}
