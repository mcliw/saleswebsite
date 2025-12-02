package com.example.saleswebsite.controller;

import com.example.saleswebsite.dto.AttributeDTO;
import com.example.saleswebsite.service.AttributeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attributes")
public class AttributeController {

    private final AttributeService attributeService;

    public AttributeController(AttributeService attributeService) {
        this.attributeService = attributeService;
    }

    @GetMapping
    public ResponseEntity<List<AttributeDTO>> getAll() {
        return ResponseEntity.ok(attributeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttributeDTO> getById(@PathVariable Long id) {
        AttributeDTO dto = attributeService.findById(id);
        if (dto == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<AttributeDTO> create(@RequestBody AttributeDTO dto) {
        return ResponseEntity.ok(attributeService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttributeDTO> update(@PathVariable Long id, @RequestBody AttributeDTO dto) {
        AttributeDTO updated = attributeService.update(id, dto);
        if (updated == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        attributeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-name")
    public ResponseEntity<AttributeDTO> byName(@RequestParam String name) {
        AttributeDTO dto = attributeService.findByName(name);
        if (dto == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }
}
