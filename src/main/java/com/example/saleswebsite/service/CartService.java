package com.example.saleswebsite.service;

import com.example.saleswebsite.dto.CartDTO;
import java.util.List;

public interface CartService {
    List<CartDTO> findAll();

    CartDTO findById(Long id);

    CartDTO create(CartDTO dto);

    CartDTO update(Long id, CartDTO dto);

    void delete(Long id);

    CartDTO findByUserId(Long userId);
}
