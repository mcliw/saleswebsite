package com.example.saleswebsite.service;

import com.example.saleswebsite.dto.ContentBlockDTO;

import java.util.List;

public interface ContentBlockService {
    List<ContentBlockDTO> findAll();

    ContentBlockDTO findById(Long id);

    ContentBlockDTO create(ContentBlockDTO dto);

    ContentBlockDTO update(Long id, ContentBlockDTO dto);

    void delete(Long id);

    ContentBlockDTO findBySlug(String slug);
}
