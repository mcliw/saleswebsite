package com.example.saleswebsite.service;

import com.example.saleswebsite.dto.CateAttributeLinkDTO;

import java.util.List;

public interface CateAttributeLinkService {
    List<CateAttributeLinkDTO> findAll();

    CateAttributeLinkDTO findById(Long id);

    CateAttributeLinkDTO create(CateAttributeLinkDTO dto);

    CateAttributeLinkDTO update(Long id, CateAttributeLinkDTO dto);

    void delete(Long id);

    List<CateAttributeLinkDTO> findByCategoryId(Long categoryId);
}
