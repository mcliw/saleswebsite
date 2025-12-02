package com.example.saleswebsite.controller;

import com.example.saleswebsite.dto.CartDTO;
import com.example.saleswebsite.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<List<CartDTO>> getAll() {
        return ResponseEntity.ok(cartService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDTO> getById(@PathVariable Long id) {
        CartDTO dto = cartService.findById(id);
        if (dto == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<CartDTO> create(@RequestBody CartDTO dto) {
        return ResponseEntity.ok(cartService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartDTO> update(@PathVariable Long id, @RequestBody CartDTO dto) {
        CartDTO updated = cartService.update(id, dto);
        if (updated == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cartService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-user/{userId}")
    public ResponseEntity<CartDTO> byUser(@PathVariable Long userId) {
        CartDTO dto = cartService.findByUserId(userId);
        if (dto == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }
}
