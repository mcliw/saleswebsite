package com.example.saleswebsite.service;

import com.example.saleswebsite.dto.NewsDTO;

import java.util.List;

public interface NewsService {
    List<NewsDTO> findAll();

    NewsDTO findById(Long id);

    NewsDTO create(NewsDTO dto);

    NewsDTO update(Long id, NewsDTO dto);

    void delete(Long id);

    NewsDTO findBySlug(String slug);
}
