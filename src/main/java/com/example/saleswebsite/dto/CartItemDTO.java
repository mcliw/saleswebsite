package com.example.saleswebsite.dto;

import com.example.saleswebsite.entity.CartItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {

    private Long id;
    private Long productVariantId;
    private Integer quantity;
    private Boolean isSelected;

    public static CartItemDTO fromEntity(CartItem entity) {
        if (entity == null)
            return null;
        CartItemDTO dto = new CartItemDTO();
        dto.setId(entity.getId());
        dto.setProductVariantId(entity.getProductVariantId());
        dto.setQuantity(entity.getQuantity());
        dto.setIsSelected(entity.getIsSelected());
        return dto;
    }

    public CartItem toEntity() {
        CartItem e = new CartItem();
        e.setId(this.id);
        e.setProductVariantId(this.productVariantId);
        e.setQuantity(this.quantity);
        e.setIsSelected(this.isSelected);
        return e;
    }
}
