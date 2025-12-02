package com.example.saleswebsite.controller;

import com.example.saleswebsite.dto.PaymentDTO;
import com.example.saleswebsite.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getAll() {
        return ResponseEntity.ok(paymentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> getById(@PathVariable Long id) {
        PaymentDTO dto = paymentService.findById(id);
        if (dto == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<PaymentDTO> create(@RequestBody PaymentDTO dto) {
        return ResponseEntity.ok(paymentService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDTO> update(@PathVariable Long id, @RequestBody PaymentDTO dto) {
        PaymentDTO updated = paymentService.update(id, dto);
        if (updated == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        paymentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-code")
    public ResponseEntity<PaymentDTO> byCode(@RequestParam String code) {
        PaymentDTO dto = paymentService.findByCode(code);
        if (dto == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }
}
