package com.example.saleswebsite.service;

import com.example.saleswebsite.dto.ImgProductDTO;

import java.util.List;

public interface ImgProductService {
    List<ImgProductDTO> findAll();

    ImgProductDTO findById(Long id);

    ImgProductDTO create(ImgProductDTO dto);

    ImgProductDTO update(Long id, ImgProductDTO dto);

    void delete(Long id);

    List<ImgProductDTO> findByProductId(Long productId);
}
