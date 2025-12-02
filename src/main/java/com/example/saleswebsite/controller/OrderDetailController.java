package com.example.saleswebsite.controller;

import com.example.saleswebsite.dto.OrderDetailDTO;
import com.example.saleswebsite.service.OrderDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-details")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDetailDTO>> getAll() {
        return ResponseEntity.ok(orderDetailService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailDTO> getById(@PathVariable Long id) {
        OrderDetailDTO dto = orderDetailService.findById(id);
        if (dto == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<OrderDetailDTO> create(@RequestBody OrderDetailDTO dto) {
        return ResponseEntity.ok(orderDetailService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDetailDTO> update(@PathVariable Long id, @RequestBody OrderDetailDTO dto) {
        OrderDetailDTO updated = orderDetailService.update(id, dto);
        if (updated == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderDetailService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-order/{orderId}")
    public ResponseEntity<List<OrderDetailDTO>> byOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderDetailService.findByOrderId(orderId));
    }
}
