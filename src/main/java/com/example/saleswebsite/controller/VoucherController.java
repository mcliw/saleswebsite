package com.example.saleswebsite.controller;

import com.example.saleswebsite.dto.VoucherDTO;
import com.example.saleswebsite.service.VoucherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vouchers")
public class VoucherController {

    private final VoucherService service;

    public VoucherController(VoucherService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<VoucherDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoucherDTO> getById(@PathVariable Long id) {
        VoucherDTO dto = service.findById(id);
        if (dto == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<VoucherDTO> create(@RequestBody VoucherDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VoucherDTO> update(@PathVariable Long id, @RequestBody VoucherDTO dto) {
        VoucherDTO updated = service.update(id, dto);
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
    public ResponseEntity<VoucherDTO> byCode(@RequestParam String code) {
        VoucherDTO dto = service.findByCode(code);
        if (dto == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }
}
