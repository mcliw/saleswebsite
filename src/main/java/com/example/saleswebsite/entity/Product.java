package com.example.saleswebsite.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userid")
    private Long userId;

    private String name;

    @Column(name = "avg_rating")
    private Double avgRating;

    @Column(name = "total_sold")
    private Integer totalSold;

    @Column(name = "total_stock")
    private Integer totalStock;

    private String description;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "review_id")
    private Long reviewId;

}
