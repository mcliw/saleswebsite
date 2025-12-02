package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.dto.PaymentDTO;
import com.example.saleswebsite.entity.Payment;
import com.example.saleswebsite.repository.PaymentRepository;
import com.example.saleswebsite.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<PaymentDTO> findAll() {
        return paymentRepository.findAll().stream().map(PaymentDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public PaymentDTO findById(Long id) {
        return paymentRepository.findById(id).map(PaymentDTO::fromEntity).orElse(null);
    }

    @Override
    public PaymentDTO create(PaymentDTO dto) {
        Payment saved = paymentRepository.save(dto.toEntity());
        return PaymentDTO.fromEntity(saved);
    }

    @Override
    public PaymentDTO update(Long id, PaymentDTO dto) {
        return paymentRepository.findById(id).map(existing -> {
            Payment updated = dto.toEntity();
            updated.setId(id);
            Payment saved = paymentRepository.save(updated);
            return PaymentDTO.fromEntity(saved);
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public PaymentDTO findByCode(String code) {
        return null;
    }
}
