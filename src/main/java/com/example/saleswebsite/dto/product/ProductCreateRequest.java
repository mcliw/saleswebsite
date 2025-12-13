package com.example.saleswebsite.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class ProductCreateRequest {
    @NotNull
    private Long userId;

    @NotBlank
    @Size(max = 255)
    private String name;

    private String description;

    private Boolean isActive = true;

    private List<ProductVariantRequest> variants;
}
