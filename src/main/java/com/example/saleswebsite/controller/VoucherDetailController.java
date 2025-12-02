package com.example.saleswebsite.controller;

import com.example.saleswebsite.dto.VoucherDetailDTO;
import com.example.saleswebsite.service.VoucherDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/voucher-details")
public class VoucherDetailController {

    private final VoucherDetailService service;

    public VoucherDetailController(VoucherDetailService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<VoucherDetailDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoucherDetailDTO> getById(@PathVariable Long id) {
        VoucherDetailDTO dto = service.findById(id);
        if (dto == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<VoucherDetailDTO> create(@RequestBody VoucherDetailDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VoucherDetailDTO> update(@PathVariable Long id, @RequestBody VoucherDetailDTO dto) {
        VoucherDetailDTO updated = service.update(id, dto);
        if (updated == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-voucher/{voucherId}")
    public ResponseEntity<List<VoucherDetailDTO>> byVoucher(@PathVariable Long voucherId) {
        return ResponseEntity.ok(service.findByVoucherId(voucherId));
    }
}
