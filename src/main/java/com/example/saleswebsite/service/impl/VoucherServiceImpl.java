package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.dto.VoucherDTO;
import com.example.saleswebsite.entity.Voucher;
import com.example.saleswebsite.repository.VoucherRepository;
import com.example.saleswebsite.service.VoucherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.saleswebsite.exception.ApiException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoucherServiceImpl implements VoucherService {
    private static final Logger log = LoggerFactory.getLogger(VoucherServiceImpl.class);

    private final VoucherRepository repository;

    public VoucherServiceImpl(VoucherRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<VoucherDTO> findAll() {
        return repository.findAll().stream().map(VoucherDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public VoucherDTO findById(Long id) {
        return repository.findById(id).map(VoucherDTO::fromEntity).orElse(null);
    }

    @Override
    @Transactional
    public VoucherDTO create(VoucherDTO dto) {
        log.info("Creating voucher for voucherDetail {}", dto.getVoucherDetailId());
        Voucher saved = repository.save(dto.toEntity());
        return VoucherDTO.fromEntity(saved);
    }

    @Override
    @Transactional
    public VoucherDTO update(Long id, VoucherDTO dto) {
        return repository.findById(id).map(existing -> {
            log.info("Updating voucher {}", id);
            Voucher updated = dto.toEntity();
            updated.setId(id);
            Voucher saved = repository.save(updated);
            return VoucherDTO.fromEntity(saved);
        }).orElseThrow(() -> new ApiException("Voucher not found"));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting voucher {}", id);
        repository.deleteById(id);
    }

    @Override
    public VoucherDTO findByCode(String code) {
        return null;
    }
}
