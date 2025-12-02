package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.dto.ContentBlockDTO;
import com.example.saleswebsite.entity.ContentBlock;
import com.example.saleswebsite.repository.ContentBlockRepository;
import com.example.saleswebsite.service.ContentBlockService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentBlockServiceImpl implements ContentBlockService {

    private final ContentBlockRepository contentBlockRepository;

    public ContentBlockServiceImpl(ContentBlockRepository contentBlockRepository) {
        this.contentBlockRepository = contentBlockRepository;
    }

    @Override
    public List<ContentBlockDTO> findAll() {
        return contentBlockRepository.findAll().stream().map(ContentBlockDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public ContentBlockDTO findById(Long id) {
        return contentBlockRepository.findById(id).map(ContentBlockDTO::fromEntity).orElse(null);
    }

    @Override
    public ContentBlockDTO create(ContentBlockDTO dto) {
        ContentBlock saved = contentBlockRepository.save(dto.toEntity());
        return ContentBlockDTO.fromEntity(saved);
    }

    @Override
    public ContentBlockDTO update(Long id, ContentBlockDTO dto) {
        return contentBlockRepository.findById(id).map(existing -> {
            ContentBlock updated = dto.toEntity();
            updated.setId(id);
            ContentBlock saved = contentBlockRepository.save(updated);
            return ContentBlockDTO.fromEntity(saved);
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        contentBlockRepository.deleteById(id);
    }

    @Override
    public ContentBlockDTO findBySlug(String slug) {
        return null;
    }
}
