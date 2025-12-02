package com.example.saleswebsite.service;

import com.example.saleswebsite.dto.PaymentDTO;

import java.util.List;

public interface PaymentService {
    List<PaymentDTO> findAll();

    PaymentDTO findById(Long id);

    PaymentDTO create(PaymentDTO dto);

    PaymentDTO update(Long id, PaymentDTO dto);

    void delete(Long id);

    PaymentDTO findByCode(String code);
}
