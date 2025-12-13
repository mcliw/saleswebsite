package com.example.saleswebsite.dto.product;

import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {
    private Long id;
    private Long userId;
    private String name;
    private String description;
    private Boolean isActive;
    private List<ProductVariantResponse> variants;
}
