package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.dto.NewsDTO;
import com.example.saleswebsite.entity.News;
import com.example.saleswebsite.repository.NewsRepository;
import com.example.saleswebsite.service.NewsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public List<NewsDTO> findAll() {
        return newsRepository.findAll().stream().map(NewsDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public NewsDTO findById(Long id) {
        return newsRepository.findById(id).map(NewsDTO::fromEntity).orElse(null);
    }

    @Override
    public NewsDTO create(NewsDTO dto) {
        News saved = newsRepository.save(dto.toEntity());
        return NewsDTO.fromEntity(saved);
    }

    @Override
    public NewsDTO update(Long id, NewsDTO dto) {
        return newsRepository.findById(id).map(existing -> {
            News updated = dto.toEntity();
            updated.setId(id);
            News saved = newsRepository.save(updated);
            return NewsDTO.fromEntity(saved);
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        newsRepository.deleteById(id);
    }

    @Override
    public NewsDTO findBySlug(String slug) {
        return newsRepository.findBySlug(slug) == null ? null : NewsDTO.fromEntity(newsRepository.findBySlug(slug));
    }
}
