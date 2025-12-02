package com.example.saleswebsite.controller;

import com.example.saleswebsite.dto.ImgProductDTO;
import com.example.saleswebsite.service.ImgProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/img-products")
public class ImgProductController {

    private final ImgProductService imgProductService;

    public ImgProductController(ImgProductService imgProductService) {
        this.imgProductService = imgProductService;
    }

    @GetMapping
    public ResponseEntity<List<ImgProductDTO>> getAll() {
        return ResponseEntity.ok(imgProductService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImgProductDTO> getById(@PathVariable Long id) {
        ImgProductDTO dto = imgProductService.findById(id);
        if (dto == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ImgProductDTO> create(@RequestBody ImgProductDTO dto) {
        return ResponseEntity.ok(imgProductService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImgProductDTO> update(@PathVariable Long id, @RequestBody ImgProductDTO dto) {
        ImgProductDTO updated = imgProductService.update(id, dto);
        if (updated == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        imgProductService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-product/{productId}")
    public ResponseEntity<List<ImgProductDTO>> byProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(imgProductService.findByProductId(productId));
    }
}
