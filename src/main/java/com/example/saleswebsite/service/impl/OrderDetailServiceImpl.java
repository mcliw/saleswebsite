package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.dto.OrderDetailDTO;
import com.example.saleswebsite.entity.OrderDetail;
import com.example.saleswebsite.repository.OrderDetailRepository;
import com.example.saleswebsite.service.OrderDetailService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public List<OrderDetailDTO> findAll() {
        return orderDetailRepository.findAll().stream().map(OrderDetailDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public OrderDetailDTO findById(Long id) {
        return orderDetailRepository.findById(id).map(OrderDetailDTO::fromEntity).orElse(null);
    }

    @Override
    public OrderDetailDTO create(OrderDetailDTO dto) {
        OrderDetail e = dto.toEntity();
        OrderDetail saved = orderDetailRepository.save(e);
        return OrderDetailDTO.fromEntity(saved);
    }

    @Override
    public OrderDetailDTO update(Long id, OrderDetailDTO dto) {
        return orderDetailRepository.findById(id).map(existing -> {
            OrderDetail updated = dto.toEntity();
            updated.setId(id);
            OrderDetail saved = orderDetailRepository.save(updated);
            return OrderDetailDTO.fromEntity(saved);
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        orderDetailRepository.deleteById(id);
    }

    @Override
    public List<OrderDetailDTO> findByOrderId(Long orderId) {
        return orderDetailRepository.findByOrderId(orderId).stream().map(OrderDetailDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
