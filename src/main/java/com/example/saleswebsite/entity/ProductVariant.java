package com.example.saleswebsite.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product_variant")
public class ProductVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    private String name;
    private Integer stock;
    private Integer sold;

    @Column(name = "original_price")
    private Double originalPrice;

    private Double price;

    @Column(name = "discount_amount")
    private Double discountAmount;

    @Column(name = "discount_percent")
    private Double discountPercent;

    @Column(name = "product_series_id")
    private Long productSeriesId;

    @Column(name = "img_product_id")
    private Long imgProductId;
}
