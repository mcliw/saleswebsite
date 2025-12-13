package com.example.saleswebsite.service;

import com.example.saleswebsite.dto.product.ProductCreateRequest;
import com.example.saleswebsite.dto.product.ProductResponse;
import java.util.List;

public interface ProductService {
    List<ProductResponse> findAll();

    ProductResponse findById(Long id);

    ProductResponse create(ProductCreateRequest request);

    ProductResponse update(Long id, ProductCreateRequest request);

    void delete(Long id);

    List<ProductResponse> findByName(String name);
}
