package com.example.saleswebsite.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cate_attribute_link")
public class CateAttributeLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "attribute_id")
    private Long attributeId;

    @Column(name = "category_id")
    private Long categoryId;

}
