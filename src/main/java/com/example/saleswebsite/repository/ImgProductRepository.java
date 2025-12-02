package com.example.saleswebsite.repository;

import com.example.saleswebsite.entity.ImgProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImgProductRepository extends JpaRepository<ImgProduct, Long> {
    ImgProduct findByImage(String image);
}
