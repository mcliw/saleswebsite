package com.example.saleswebsite.dto;

import com.example.saleswebsite.entity.ProductVariant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariantDTO {

    private Long id;
    private Long productId;
    private String name;
    private Integer stock;
    private Integer sold;
    private Double originalPrice;
    private Double price;
    private Double discountAmount;
    private Double discountPercent;
    private Long productSeriesId;
    private Long imgProductId;

    public static ProductVariantDTO fromEntity(ProductVariant entity) {
        if (entity == null)
            return null;
        ProductVariantDTO dto = new ProductVariantDTO();
        dto.setId(entity.getId());
        dto.setProductId(entity.getProductId());
        dto.setName(entity.getName());
        dto.setStock(entity.getStock());
        dto.setSold(entity.getSold());
        dto.setOriginalPrice(entity.getOriginalPrice());
        dto.setPrice(entity.getPrice());
        dto.setDiscountAmount(entity.getDiscountAmount());
        dto.setDiscountPercent(entity.getDiscountPercent());
        dto.setProductSeriesId(entity.getProductSeriesId());
        dto.setImgProductId(entity.getImgProductId());
        return dto;
    }

    public ProductVariant toEntity() {
        ProductVariant e = new ProductVariant();
        e.setId(this.id);
        e.setProductId(this.productId);
        e.setName(this.name);
        e.setStock(this.stock);
        e.setSold(this.sold);
        e.setOriginalPrice(this.originalPrice);
        e.setPrice(this.price);
        e.setDiscountAmount(this.discountAmount);
        e.setDiscountPercent(this.discountPercent);
        e.setProductSeriesId(this.productSeriesId);
        e.setImgProductId(this.imgProductId);
        return e;
    }
}
