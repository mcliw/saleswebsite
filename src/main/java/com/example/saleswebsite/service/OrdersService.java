package com.example.saleswebsite.service;

import com.example.saleswebsite.dto.OrdersDTO;
import java.util.List;

public interface OrdersService {
    List<OrdersDTO> findAll();

    OrdersDTO findById(Long id);

    OrdersDTO create(OrdersDTO dto);

    OrdersDTO update(Long id, OrdersDTO dto);

    void delete(Long id);

    List<OrdersDTO> findByUserId(Long userId);

    OrdersDTO findByOrderCode(String orderCode);
}
