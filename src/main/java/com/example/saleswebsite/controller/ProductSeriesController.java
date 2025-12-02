package com.example.saleswebsite.controller;

import com.example.saleswebsite.dto.ProductSeriesDTO;
import com.example.saleswebsite.service.ProductSeriesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-series")
public class ProductSeriesController {

    private final ProductSeriesService service;

    public ProductSeriesController(ProductSeriesService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProductSeriesDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductSeriesDTO> getById(@PathVariable Long id) {
        ProductSeriesDTO dto = service.findById(id);
        if (dto == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ProductSeriesDTO> create(@RequestBody ProductSeriesDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductSeriesDTO> update(@PathVariable Long id, @RequestBody ProductSeriesDTO dto) {
        ProductSeriesDTO updated = service.update(id, dto);
        if (updated == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-code")
    public ResponseEntity<ProductSeriesDTO> byCode(@RequestParam String code) {
        ProductSeriesDTO dto = service.findByCode(code);
        if (dto == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }
}
