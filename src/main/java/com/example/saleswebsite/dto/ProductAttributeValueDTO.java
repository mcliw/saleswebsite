package com.example.saleswebsite.dto;

import com.example.saleswebsite.entity.ProductAttributeValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductAttributeValueDTO {

    private Long id;
    private String value;
    private Long attributeId;
    private Long productId;

    public static ProductAttributeValueDTO fromEntity(ProductAttributeValue entity) {
        if (entity == null)
            return null;
        ProductAttributeValueDTO dto = new ProductAttributeValueDTO();
        dto.setId(entity.getId());
        dto.setValue(entity.getValue());
        dto.setAttributeId(entity.getAttributeId());
        dto.setProductId(entity.getProductId());
        return dto;
    }

    public ProductAttributeValue toEntity() {
        ProductAttributeValue e = new ProductAttributeValue();
        e.setId(this.id);
        e.setValue(this.value);
        e.setAttributeId(this.attributeId);
        e.setProductId(this.productId);
        return e;
    }
}
