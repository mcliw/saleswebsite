package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.dto.OrdersDTO;
import com.example.saleswebsite.entity.Orders;
import com.example.saleswebsite.repository.OrdersRepository;
import com.example.saleswebsite.service.OrdersService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdersServiceImpl implements OrdersService {

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
    public OrdersDTO create(OrdersDTO dto) {
        Orders e = dto.toEntity();
        Orders saved = ordersRepository.save(e);
        return OrdersDTO.fromEntity(saved);
    }

    @Override
    public OrdersDTO update(Long id, OrdersDTO dto) {
        return ordersRepository.findById(id).map(existing -> {
            Orders updated = dto.toEntity();
            updated.setId(id);
            Orders saved = ordersRepository.save(updated);
            return OrdersDTO.fromEntity(saved);
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
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
