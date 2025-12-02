package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.dto.CartDTO;
import com.example.saleswebsite.entity.Cart;
import com.example.saleswebsite.repository.CartRepository;
import com.example.saleswebsite.service.CartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public List<CartDTO> findAll() {
        return cartRepository.findAll().stream().map(CartDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public CartDTO findById(Long id) {
        return cartRepository.findById(id).map(CartDTO::fromEntity).orElse(null);
    }

    @Override
    public CartDTO create(CartDTO dto) {
        Cart e = dto.toEntity();
        Cart saved = cartRepository.save(e);
        return CartDTO.fromEntity(saved);
    }

    @Override
    public CartDTO update(Long id, CartDTO dto) {
        return cartRepository.findById(id).map(existing -> {
            Cart updated = dto.toEntity();
            updated.setId(id);
            Cart saved = cartRepository.save(updated);
            return CartDTO.fromEntity(saved);
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public CartDTO findByUserId(Long userId) {
        return cartRepository.findByUserId(userId).map(CartDTO::fromEntity).orElse(null);
    }
}
