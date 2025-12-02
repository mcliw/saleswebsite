package com.example.saleswebsite.controller;

import com.example.saleswebsite.dto.NewsDTO;
import com.example.saleswebsite.service.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public ResponseEntity<List<NewsDTO>> getAll() {
        return ResponseEntity.ok(newsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsDTO> getById(@PathVariable Long id) {
        NewsDTO dto = newsService.findById(id);
        if (dto == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<NewsDTO> create(@RequestBody NewsDTO dto) {
        return ResponseEntity.ok(newsService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsDTO> update(@PathVariable Long id, @RequestBody NewsDTO dto) {
        NewsDTO updated = newsService.update(id, dto);
        if (updated == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        newsService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<NewsDTO> bySlug(@PathVariable String slug) {
        NewsDTO dto = newsService.findBySlug(slug);
        if (dto == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }
}
