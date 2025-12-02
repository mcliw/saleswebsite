package com.example.saleswebsite.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "order_code")
    private String orderCode;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "voucher_id")
    private Long voucherId;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "shipping_address")
    private String shippingAddress;

    @Column(name = "payment_id")
    private Long paymentId;

}
