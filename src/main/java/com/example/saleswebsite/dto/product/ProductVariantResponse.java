package com.example.saleswebsite.dto.product;

import lombok.Data;

@Data
public class ProductVariantResponse {
    private Long id;
    private String name;
    private Integer stock;
    private Double price;
}
