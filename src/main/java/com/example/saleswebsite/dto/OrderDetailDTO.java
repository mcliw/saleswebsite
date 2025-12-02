package com.example.saleswebsite.dto;

import com.example.saleswebsite.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {

    private Long id;
    private Long orderId;
    private Long productVariantId;
    private Integer quantity;

    public static OrderDetailDTO fromEntity(OrderDetail entity) {
        if (entity == null)
            return null;
        OrderDetailDTO dto = new OrderDetailDTO();
        dto.setId(entity.getId());
        dto.setOrderId(entity.getOrderId());
        dto.setProductVariantId(entity.getProductVariantId());
        dto.setQuantity(entity.getQuantity());
        return dto;
    }

    public OrderDetail toEntity() {
        OrderDetail e = new OrderDetail();
        e.setId(this.id);
        e.setOrderId(this.orderId);
        e.setProductVariantId(this.productVariantId);
        e.setQuantity(this.quantity);
        return e;
    }
}
