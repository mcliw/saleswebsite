package com.example.saleswebsite.dto;

import com.example.saleswebsite.entity.ProductSeries;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSeriesDTO {

    private Long id;
    private String name;
    private String slug;
    private Long brandId;
    private Long categoryId;

    public static ProductSeriesDTO fromEntity(ProductSeries entity) {
        if (entity == null)
            return null;
        ProductSeriesDTO dto = new ProductSeriesDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSlug(entity.getSlug());
        dto.setBrandId(entity.getBrandId());
        dto.setCategoryId(entity.getCategoryId());
        return dto;
    }

    public ProductSeries toEntity() {
        ProductSeries e = new ProductSeries();
        e.setId(this.id);
        e.setName(this.name);
        e.setSlug(this.slug);
        e.setBrandId(this.brandId);
        e.setCategoryId(this.categoryId);
        return e;
    }
}
