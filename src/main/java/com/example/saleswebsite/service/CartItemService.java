package com.example.saleswebsite.service;

import com.example.saleswebsite.dto.CartItemDTO;
import java.util.List;

public interface CartItemService {
    List<CartItemDTO> findAll();

    CartItemDTO findById(Long id);

    CartItemDTO create(CartItemDTO dto);

    CartItemDTO update(Long id, CartItemDTO dto);

    void delete(Long id);

    List<CartItemDTO> findByProductVariantId(Long productVariantId);
}
