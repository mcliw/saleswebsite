package com.example.saleswebsite.dto;

import com.example.saleswebsite.entity.Orders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDTO {

    private Long id;
    private Long userId;
    private String orderCode;
    private Double totalPrice;
    private Long voucherId;
    private String orderStatus;
    private String paymentMethod;
    private String shippingAddress;
    private Long paymentId;

    public static OrdersDTO fromEntity(Orders entity) {
        if (entity == null)
            return null;
        OrdersDTO dto = new OrdersDTO();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUserId());
        dto.setOrderCode(entity.getOrderCode());
        dto.setTotalPrice(entity.getTotalPrice());
        dto.setVoucherId(entity.getVoucherId());
        dto.setOrderStatus(entity.getOrderStatus());
        dto.setPaymentMethod(entity.getPaymentMethod());
        dto.setShippingAddress(entity.getShippingAddress());
        dto.setPaymentId(entity.getPaymentId());
        return dto;
    }

    public Orders toEntity() {
        Orders e = new Orders();
        e.setId(this.id);
        e.setUserId(this.userId);
        e.setOrderCode(this.orderCode);
        e.setTotalPrice(this.totalPrice);
        e.setVoucherId(this.voucherId);
        e.setOrderStatus(this.orderStatus);
        e.setPaymentMethod(this.paymentMethod);
        e.setShippingAddress(this.shippingAddress);
        e.setPaymentId(this.paymentId);
        return e;
    }
}
