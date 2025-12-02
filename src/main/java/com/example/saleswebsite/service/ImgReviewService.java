package com.example.saleswebsite.service;

import com.example.saleswebsite.dto.ImgReviewDTO;

import java.util.List;

public interface ImgReviewService {
    List<ImgReviewDTO> findAll();

    ImgReviewDTO findById(Long id);

    ImgReviewDTO create(ImgReviewDTO dto);

    ImgReviewDTO update(Long id, ImgReviewDTO dto);

    void delete(Long id);

    List<ImgReviewDTO> findByReviewId(Long reviewId);
}
