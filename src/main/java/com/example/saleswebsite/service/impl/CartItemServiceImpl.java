package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.dto.CartItemDTO;
import com.example.saleswebsite.entity.CartItem;
import com.example.saleswebsite.repository.CartItemRepository;
import com.example.saleswebsite.service.CartItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.saleswebsite.exception.ApiException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartItemServiceImpl implements CartItemService {
    private static final Logger log = LoggerFactory.getLogger(CartItemServiceImpl.class);

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
    @Transactional
    public CartItemDTO create(CartItemDTO dto) {
        log.info("Creating cart item for productVariant {}", dto.getProductVariantId());
        CartItem e = dto.toEntity();
        CartItem saved = cartItemRepository.save(e);
        return CartItemDTO.fromEntity(saved);
    }

    @Override
    @Transactional
    public CartItemDTO update(Long id, CartItemDTO dto) {
        return cartItemRepository.findById(id).map(existing -> {
            log.info("Updating cart item {}", id);
            CartItem updated = dto.toEntity();
            updated.setId(id);
            CartItem saved = cartItemRepository.save(updated);
            return CartItemDTO.fromEntity(saved);
        }).orElseThrow(() -> new ApiException("CartItem not found"));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting cart item {}", id);
        cartItemRepository.deleteById(id);
    }

    @Override
    public List<CartItemDTO> findByProductVariantId(Long productVariantId) {
        return cartItemRepository.findByProductVariantId(productVariantId).stream().map(CartItemDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
