package com.example.saleswebsite.controller;

import com.example.saleswebsite.dto.CartItemDTO;
import com.example.saleswebsite.service.CartItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {

    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping
    public ResponseEntity<List<CartItemDTO>> getAll() {
        return ResponseEntity.ok(cartItemService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItemDTO> getById(@PathVariable Long id) {
        CartItemDTO dto = cartItemService.findById(id);
        if (dto == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<CartItemDTO> create(@RequestBody CartItemDTO dto) {
        return ResponseEntity.ok(cartItemService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartItemDTO> update(@PathVariable Long id, @RequestBody CartItemDTO dto) {
        CartItemDTO updated = cartItemService.update(id, dto);
        if (updated == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cartItemService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-product-variant/{productVariantId}")
    public ResponseEntity<List<CartItemDTO>> byProductVariant(@PathVariable Long productVariantId) {
        return ResponseEntity.ok(cartItemService.findByProductVariantId(productVariantId));
    }
}
