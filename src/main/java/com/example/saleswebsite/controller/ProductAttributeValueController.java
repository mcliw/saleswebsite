package com.example.saleswebsite.controller;

import com.example.saleswebsite.dto.ProductAttributeValueDTO;
import com.example.saleswebsite.service.ProductAttributeValueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-attribute-values")
public class ProductAttributeValueController {

    private final ProductAttributeValueService service;

    public ProductAttributeValueController(ProductAttributeValueService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProductAttributeValueDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductAttributeValueDTO> getById(@PathVariable Long id) {
        ProductAttributeValueDTO dto = service.findById(id);
        if (dto == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ProductAttributeValueDTO> create(@RequestBody ProductAttributeValueDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductAttributeValueDTO> update(@PathVariable Long id,
            @RequestBody ProductAttributeValueDTO dto) {
        ProductAttributeValueDTO updated = service.update(id, dto);
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
    public ResponseEntity<List<ProductAttributeValueDTO>> byProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(service.findByProductId(productId));
    }
}
