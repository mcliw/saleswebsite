package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.dto.AttributeDTO;
import com.example.saleswebsite.entity.Attribute;
import com.example.saleswebsite.repository.AttributeRepository;
import com.example.saleswebsite.service.AttributeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttributeServiceImpl implements AttributeService {

    private final AttributeRepository attributeRepository;

    public AttributeServiceImpl(AttributeRepository attributeRepository) {
        this.attributeRepository = attributeRepository;
    }

    @Override
    public List<AttributeDTO> findAll() {
        return attributeRepository.findAll().stream().map(AttributeDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public AttributeDTO findById(Long id) {
        return attributeRepository.findById(id).map(AttributeDTO::fromEntity).orElse(null);
    }

    @Override
    public AttributeDTO create(AttributeDTO dto) {
        Attribute e = dto.toEntity();
        Attribute saved = attributeRepository.save(e);
        return AttributeDTO.fromEntity(saved);
    }

    @Override
    public AttributeDTO update(Long id, AttributeDTO dto) {
        return attributeRepository.findById(id).map(existing -> {
            Attribute updated = dto.toEntity();
            updated.setId(id);
            Attribute saved = attributeRepository.save(updated);
            return AttributeDTO.fromEntity(saved);
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        attributeRepository.deleteById(id);
    }

    @Override
    public AttributeDTO findByName(String name) {
        return attributeRepository.findByName(name) == null ? null
                : AttributeDTO.fromEntity(attributeRepository.findByName(name));
    }
}
