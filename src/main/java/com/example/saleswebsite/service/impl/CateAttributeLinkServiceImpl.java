package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.dto.CateAttributeLinkDTO;
import com.example.saleswebsite.entity.CateAttributeLink;
import com.example.saleswebsite.repository.CateAttributeLinkRepository;
import com.example.saleswebsite.service.CateAttributeLinkService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CateAttributeLinkServiceImpl implements CateAttributeLinkService {

    private final CateAttributeLinkRepository repository;

    public CateAttributeLinkServiceImpl(CateAttributeLinkRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CateAttributeLinkDTO> findAll() {
        return repository.findAll().stream().map(CateAttributeLinkDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public CateAttributeLinkDTO findById(Long id) {
        return repository.findById(id).map(CateAttributeLinkDTO::fromEntity).orElse(null);
    }

    @Override
    public CateAttributeLinkDTO create(CateAttributeLinkDTO dto) {
        CateAttributeLink saved = repository.save(dto.toEntity());
        return CateAttributeLinkDTO.fromEntity(saved);
    }

    @Override
    public CateAttributeLinkDTO update(Long id, CateAttributeLinkDTO dto) {
        return repository.findById(id).map(existing -> {
            CateAttributeLink updated = dto.toEntity();
            updated.setId(id);
            CateAttributeLink saved = repository.save(updated);
            return CateAttributeLinkDTO.fromEntity(saved);
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<CateAttributeLinkDTO> findByCategoryId(Long categoryId) {
        return repository.findByCategoryId(categoryId).stream().map(CateAttributeLinkDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
