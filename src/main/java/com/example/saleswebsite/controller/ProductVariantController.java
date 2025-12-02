package com.example.saleswebsite.controller;

import com.example.saleswebsite.dto.ProductVariantDTO;
import com.example.saleswebsite.service.ProductVariantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-variants")
public class ProductVariantController {

    private final ProductVariantService service;

    public ProductVariantController(ProductVariantService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProductVariantDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductVariantDTO> getById(@PathVariable Long id) {
        ProductVariantDTO dto = service.findById(id);
        if (dto == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ProductVariantDTO> create(@RequestBody ProductVariantDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductVariantDTO> update(@PathVariable Long id, @RequestBody ProductVariantDTO dto) {
        ProductVariantDTO updated = service.update(id, dto);
        if (updated == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-product/{productId}")
    public ResponseEntity<List<ProductVariantDTO>> byProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(service.findByProductId(productId));
    }
}
