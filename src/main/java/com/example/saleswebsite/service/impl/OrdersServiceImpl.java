package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.dto.OrdersDTO;
import com.example.saleswebsite.entity.Orders;
import com.example.saleswebsite.repository.OrdersRepository;
import com.example.saleswebsite.service.OrdersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.saleswebsite.exception.ApiException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdersServiceImpl implements OrdersService {
    private static final Logger log = LoggerFactory.getLogger(OrdersServiceImpl.class);

    private final OrdersRepository ordersRepository;

    public OrdersServiceImpl(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public List<OrdersDTO> findAll() {
        return ordersRepository.findAll().stream().map(OrdersDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public OrdersDTO findById(Long id) {
        return ordersRepository.findById(id).map(OrdersDTO::fromEntity).orElse(null);
    }

    @Override
    @Transactional
    public OrdersDTO create(OrdersDTO dto) {
        log.info("Creating order for user {}", dto.getUserId());
        Orders e = dto.toEntity();
        Orders saved = ordersRepository.save(e);
        return OrdersDTO.fromEntity(saved);
    }

    @Override
    @Transactional
    public OrdersDTO update(Long id, OrdersDTO dto) {
        return ordersRepository.findById(id).map(existing -> {
            log.info("Updating order {}", id);
            Orders updated = dto.toEntity();
            updated.setId(id);
            Orders saved = ordersRepository.save(updated);
            return OrdersDTO.fromEntity(saved);
        }).orElseThrow(() -> new ApiException("Order not found"));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting order {}", id);
        ordersRepository.deleteById(id);
    }

    @Override
    public List<OrdersDTO> findByUserId(Long userId) {
        return ordersRepository.findByUserId(userId).stream().map(OrdersDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public OrdersDTO findByOrderCode(String orderCode) {
        return ordersRepository.findByOrderCode(orderCode).map(OrdersDTO::fromEntity).orElse(null);
    }
}
