package com.example.saleswebsite.controller;

import com.example.saleswebsite.dto.ApiResponse;
import com.example.saleswebsite.dto.product.ProductCreateRequest;
import com.example.saleswebsite.dto.product.ProductResponse;
import com.example.saleswebsite.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getAll() {
        return ResponseEntity.ok(ApiResponse.ok(productService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> getById(@PathVariable Long id) {
        ProductResponse dto = productService.findById(id);
        if (dto == null)
            return ResponseEntity.ok(ApiResponse.fail("Not found"));
        return ResponseEntity.ok(ApiResponse.ok(dto));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> create(@Valid @RequestBody ProductCreateRequest req) {
        ProductResponse created = productService.create(req);
        return ResponseEntity.ok(ApiResponse.ok(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> update(@PathVariable Long id,
            @Valid @RequestBody ProductCreateRequest req) {
        ProductResponse updated = productService.update(id, req);
        if (updated == null)
            return ResponseEntity.ok(ApiResponse.fail("Not found"));
        return ResponseEntity.ok(ApiResponse.ok(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok(ApiResponse.ok(null));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> searchByName(
            @RequestParam(required = false) String name) {
        return ResponseEntity.ok(ApiResponse.ok(productService.findByName(name)));
    }
}
