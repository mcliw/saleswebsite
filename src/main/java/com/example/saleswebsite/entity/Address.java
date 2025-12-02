package com.example.saleswebsite.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userid")
    private Long userId;

    private String city;
    private String district;
    private String ward;

    @Column(name = "street_address")
    private String streetAddress;

    @Column(name = "is_default")
    private Boolean isDefault;

}
