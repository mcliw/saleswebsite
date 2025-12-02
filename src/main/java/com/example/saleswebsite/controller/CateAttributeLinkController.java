package com.example.saleswebsite.controller;

import com.example.saleswebsite.dto.CateAttributeLinkDTO;
import com.example.saleswebsite.service.CateAttributeLinkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cate-attribute-links")
public class CateAttributeLinkController {

    private final CateAttributeLinkService service;

    public CateAttributeLinkController(CateAttributeLinkService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CateAttributeLinkDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CateAttributeLinkDTO> getById(@PathVariable Long id) {
        CateAttributeLinkDTO dto = service.findById(id);
        if (dto == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<CateAttributeLinkDTO> create(@RequestBody CateAttributeLinkDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CateAttributeLinkDTO> update(@PathVariable Long id, @RequestBody CateAttributeLinkDTO dto) {
        CateAttributeLinkDTO updated = service.update(id, dto);
        if (updated == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-category/{categoryId}")
    public ResponseEntity<List<CateAttributeLinkDTO>> byCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(service.findByCategoryId(categoryId));
    }
}
