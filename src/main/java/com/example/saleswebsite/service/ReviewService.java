package com.example.saleswebsite.service;

import com.example.saleswebsite.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    List<ReviewDTO> findAll();

    ReviewDTO findById(Long id);

    ReviewDTO create(ReviewDTO dto);

    ReviewDTO update(Long id, ReviewDTO dto);

    void delete(Long id);

    List<ReviewDTO> findByProductId(Long productId);
}
