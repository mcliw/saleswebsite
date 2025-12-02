package com.example.saleswebsite.service;

import com.example.saleswebsite.dto.OrderDetailDTO;
import java.util.List;

public interface OrderDetailService {
    List<OrderDetailDTO> findAll();

    OrderDetailDTO findById(Long id);

    OrderDetailDTO create(OrderDetailDTO dto);

    OrderDetailDTO update(Long id, OrderDetailDTO dto);

    void delete(Long id);

    List<OrderDetailDTO> findByOrderId(Long orderId);
}
