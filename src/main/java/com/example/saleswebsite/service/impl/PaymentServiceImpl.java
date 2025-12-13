package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.dto.PaymentDTO;
import com.example.saleswebsite.entity.Payment;
import com.example.saleswebsite.repository.PaymentRepository;
import com.example.saleswebsite.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.saleswebsite.exception.ApiException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {
    private static final Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);

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
    @Transactional
    public PaymentDTO create(PaymentDTO dto) {
        log.info("Creating payment amount {}", dto.getAmount());
        Payment saved = paymentRepository.save(dto.toEntity());
        return PaymentDTO.fromEntity(saved);
    }

    @Override
    @Transactional
    public PaymentDTO update(Long id, PaymentDTO dto) {
        return paymentRepository.findById(id).map(existing -> {
            log.info("Updating payment {}", id);
            Payment updated = dto.toEntity();
            updated.setId(id);
            Payment saved = paymentRepository.save(updated);
            return PaymentDTO.fromEntity(saved);
        }).orElseThrow(() -> new ApiException("Payment not found"));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting payment {}", id);
        paymentRepository.deleteById(id);
    }

    @Override
    public PaymentDTO findByCode(String code) {
        return null;
    }
}
