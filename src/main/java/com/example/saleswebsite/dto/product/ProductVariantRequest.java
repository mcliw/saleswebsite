package com.example.saleswebsite.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductVariantRequest {
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private Integer stock;
    private Double price;
    private Double originalPrice;
}
