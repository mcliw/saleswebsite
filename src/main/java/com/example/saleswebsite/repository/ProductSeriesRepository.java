package com.example.saleswebsite.repository;

import com.example.saleswebsite.entity.ProductSeries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductSeriesRepository extends JpaRepository<ProductSeries, Long> {
    ProductSeries findBySlug(String slug);

    List<ProductSeries> findByBrandId(Long brandId);

    List<ProductSeries> findByCategoryId(Long categoryId);

    List<ProductSeries> findByBrandIdAndCategoryId(Long brandId, Long categoryId);
}
