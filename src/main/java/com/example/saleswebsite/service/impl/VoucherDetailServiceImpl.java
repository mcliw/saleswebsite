package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.dto.VoucherDetailDTO;
import com.example.saleswebsite.entity.VoucherDetail;
import com.example.saleswebsite.repository.VoucherDetailRepository;
import com.example.saleswebsite.service.VoucherDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.saleswebsite.exception.ApiException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoucherDetailServiceImpl implements VoucherDetailService {
    private static final Logger log = LoggerFactory.getLogger(VoucherDetailServiceImpl.class);

    private final VoucherDetailRepository repository;

    public VoucherDetailServiceImpl(VoucherDetailRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<VoucherDetailDTO> findAll() {
        return repository.findAll().stream().map(VoucherDetailDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public VoucherDetailDTO findById(Long id) {
        return repository.findById(id).map(VoucherDetailDTO::fromEntity).orElse(null);
    }

    @Override
    @Transactional
    public VoucherDetailDTO create(VoucherDetailDTO dto) {
        log.info("Creating voucher detail for voucher code {}", dto.getCode());
        VoucherDetail saved = repository.save(dto.toEntity());
        return VoucherDetailDTO.fromEntity(saved);
    }

    @Override
    @Transactional
    public VoucherDetailDTO update(Long id, VoucherDetailDTO dto) {
        return repository.findById(id).map(existing -> {
            log.info("Updating voucher detail {}", id);
            VoucherDetail updated = dto.toEntity();
            updated.setId(id);
            VoucherDetail saved = repository.save(updated);
            return VoucherDetailDTO.fromEntity(saved);
        }).orElseThrow(() -> new ApiException("VoucherDetail not found"));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting voucher detail {}", id);
        repository.deleteById(id);
    }

    @Override
    public List<VoucherDetailDTO> findByVoucherId(Long voucherId) {
        return repository.findAll().stream()
                .map(VoucherDetailDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
