package com.example.saleswebsite.controller;

import com.example.saleswebsite.dto.ContentBlockDTO;
import com.example.saleswebsite.service.ContentBlockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/content-blocks")
public class ContentBlockController {

    private final ContentBlockService contentBlockService;

    public ContentBlockController(ContentBlockService contentBlockService) {
        this.contentBlockService = contentBlockService;
    }

    @GetMapping
    public ResponseEntity<List<ContentBlockDTO>> getAll() {
        return ResponseEntity.ok(contentBlockService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentBlockDTO> getById(@PathVariable Long id) {
        ContentBlockDTO dto = contentBlockService.findById(id);
        if (dto == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ContentBlockDTO> create(@RequestBody ContentBlockDTO dto) {
        return ResponseEntity.ok(contentBlockService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContentBlockDTO> update(@PathVariable Long id, @RequestBody ContentBlockDTO dto) {
        ContentBlockDTO updated = contentBlockService.update(id, dto);
        if (updated == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contentBlockService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<ContentBlockDTO> bySlug(@PathVariable String slug) {
        ContentBlockDTO dto = contentBlockService.findBySlug(slug);
        if (dto == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }
}
