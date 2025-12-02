package com.example.saleswebsite.repository;

import com.example.saleswebsite.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    Brand findBySlug(String slug);

    Brand findByName(String name);
}
