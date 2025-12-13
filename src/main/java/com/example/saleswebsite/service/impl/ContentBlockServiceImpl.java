package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.dto.ContentBlockDTO;
import com.example.saleswebsite.entity.ContentBlock;
import com.example.saleswebsite.repository.ContentBlockRepository;
import com.example.saleswebsite.service.ContentBlockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.saleswebsite.exception.ApiException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentBlockServiceImpl implements ContentBlockService {
    private static final Logger log = LoggerFactory.getLogger(ContentBlockServiceImpl.class);

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
    @Transactional
    public ContentBlockDTO create(ContentBlockDTO dto) {
        log.info("Creating content block {}", dto.getTitle());
        ContentBlock saved = contentBlockRepository.save(dto.toEntity());
        return ContentBlockDTO.fromEntity(saved);
    }

    @Override
    @Transactional
    public ContentBlockDTO update(Long id, ContentBlockDTO dto) {
        return contentBlockRepository.findById(id).map(existing -> {
            log.info("Updating content block {}", id);
            ContentBlock updated = dto.toEntity();
            updated.setId(id);
            ContentBlock saved = contentBlockRepository.save(updated);
            return ContentBlockDTO.fromEntity(saved);
        }).orElseThrow(() -> new ApiException("ContentBlock not found"));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting content block {}", id);
        contentBlockRepository.deleteById(id);
    }

    @Override
    public ContentBlockDTO findBySlug(String slug) {
        return null;
    }
}
