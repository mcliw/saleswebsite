package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.dto.CartDTO;
import com.example.saleswebsite.entity.Cart;
import com.example.saleswebsite.repository.CartRepository;
import com.example.saleswebsite.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.saleswebsite.exception.ApiException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    private static final Logger log = LoggerFactory.getLogger(CartServiceImpl.class);

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
    @Transactional
    public CartDTO create(CartDTO dto) {
        log.info("Creating cart for user {}", dto.getUserId());
        Cart e = dto.toEntity();
        Cart saved = cartRepository.save(e);
        return CartDTO.fromEntity(saved);
    }

    @Override
    @Transactional
    public CartDTO update(Long id, CartDTO dto) {
        return cartRepository.findById(id).map(existing -> {
            log.info("Updating cart {}", id);
            Cart updated = dto.toEntity();
            updated.setId(id);
            Cart saved = cartRepository.save(updated);
            return CartDTO.fromEntity(saved);
        }).orElseThrow(() -> new ApiException("Cart not found"));
    }
    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting cart {}", id);
        cartRepository.deleteById(id);
    }

    @Override
    public CartDTO findByUserId(Long userId) {
        return cartRepository.findByUserId(userId).map(CartDTO::fromEntity).orElse(null);
    }
}
