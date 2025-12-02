package com.example.saleswebsite.dto;

import com.example.saleswebsite.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;
    private Long userId;
    private String name;
    private Double avgRating;
    private Integer totalSold;
    private Integer totalStock;
    private String description;
    private Boolean isActive;
    private Long reviewId;

    public static ProductDTO fromEntity(Product entity) {
        if (entity == null)
            return null;
        ProductDTO dto = new ProductDTO();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUserId());
        dto.setName(entity.getName());
        dto.setAvgRating(entity.getAvgRating());
        dto.setTotalSold(entity.getTotalSold());
        dto.setTotalStock(entity.getTotalStock());
        dto.setDescription(entity.getDescription());
        dto.setIsActive(entity.getIsActive());
        dto.setReviewId(entity.getReviewId());
        return dto;
    }

    public Product toEntity() {
        Product e = new Product();
        e.setId(this.id);
        e.setUserId(this.userId);
        e.setName(this.name);
        e.setAvgRating(this.avgRating);
        e.setTotalSold(this.totalSold);
        e.setTotalStock(this.totalStock);
        e.setDescription(this.description);
        e.setIsActive(this.isActive);
        e.setReviewId(this.reviewId);
        return e;
    }
}
