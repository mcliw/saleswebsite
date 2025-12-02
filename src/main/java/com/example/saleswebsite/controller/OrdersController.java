package com.example.saleswebsite.controller;

import com.example.saleswebsite.dto.OrdersDTO;
import com.example.saleswebsite.service.OrdersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {

    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping
    public ResponseEntity<List<OrdersDTO>> getAll() {
        return ResponseEntity.ok(ordersService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdersDTO> getById(@PathVariable Long id) {
        OrdersDTO dto = ordersService.findById(id);
        if (dto == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<OrdersDTO> create(@RequestBody OrdersDTO dto) {
        return ResponseEntity.ok(ordersService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdersDTO> update(@PathVariable Long id, @RequestBody OrdersDTO dto) {
        OrdersDTO updated = ordersService.update(id, dto);
        if (updated == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ordersService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<OrdersDTO>> byUser(@PathVariable Long userId) {
        return ResponseEntity.ok(ordersService.findByUserId(userId));
    }

    @GetMapping("/by-code")
    public ResponseEntity<OrdersDTO> byCode(@RequestParam String code) {
        OrdersDTO dto = ordersService.findByOrderCode(code);
        if (dto == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }
}
