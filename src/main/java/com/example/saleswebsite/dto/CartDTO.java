package com.example.saleswebsite.dto;

import com.example.saleswebsite.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

    private Long id;
    private Long userId;
    private Long cartItemId;

    public static CartDTO fromEntity(Cart entity) {
        if (entity == null)
            return null;
        CartDTO dto = new CartDTO();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUserId());
        dto.setCartItemId(entity.getCartItemId());
        return dto;
    }

    public Cart toEntity() {
        Cart e = new Cart();
        e.setId(this.id);
        e.setUserId(this.userId);
        e.setCartItemId(this.cartItemId);
        return e;
    }
}
