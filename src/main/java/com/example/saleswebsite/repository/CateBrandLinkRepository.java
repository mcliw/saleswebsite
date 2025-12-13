package com.example.saleswebsite.repository;

import com.example.saleswebsite.entity.CateBrandLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CateBrandLinkRepository extends JpaRepository<CateBrandLink, Long> {
    List<CateBrandLink> findByCategoryId(Long categoryId);

    List<CateBrandLink> findByBrandId(Long brandId);
}
