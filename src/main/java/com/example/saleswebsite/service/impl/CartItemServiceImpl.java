package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.dto.CartItemDTO;
import com.example.saleswebsite.entity.CartItem;
import com.example.saleswebsite.repository.CartItemRepository;
import com.example.saleswebsite.service.CartItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;

    public CartItemServiceImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public List<CartItemDTO> findAll() {
        return cartItemRepository.findAll().stream().map(CartItemDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public CartItemDTO findById(Long id) {
        return cartItemRepository.findById(id).map(CartItemDTO::fromEntity).orElse(null);
    }

    @Override
    public CartItemDTO create(CartItemDTO dto) {
        CartItem e = dto.toEntity();
        CartItem saved = cartItemRepository.save(e);
        return CartItemDTO.fromEntity(saved);
    }

    @Override
    public CartItemDTO update(Long id, CartItemDTO dto) {
        return cartItemRepository.findById(id).map(existing -> {
            CartItem updated = dto.toEntity();
            updated.setId(id);
            CartItem saved = cartItemRepository.save(updated);
            return CartItemDTO.fromEntity(saved);
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        cartItemRepository.deleteById(id);
    }

    @Override
    public List<CartItemDTO> findByProductVariantId(Long productVariantId) {
        return cartItemRepository.findByProductVariantId(productVariantId).stream().map(CartItemDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
