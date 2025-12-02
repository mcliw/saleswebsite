package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.dto.VoucherDetailDTO;
import com.example.saleswebsite.entity.VoucherDetail;
import com.example.saleswebsite.repository.VoucherDetailRepository;
import com.example.saleswebsite.service.VoucherDetailService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoucherDetailServiceImpl implements VoucherDetailService {

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
    public VoucherDetailDTO create(VoucherDetailDTO dto) {
        VoucherDetail saved = repository.save(dto.toEntity());
        return VoucherDetailDTO.fromEntity(saved);
    }

    @Override
    public VoucherDetailDTO update(Long id, VoucherDetailDTO dto) {
        return repository.findById(id).map(existing -> {
            VoucherDetail updated = dto.toEntity();
            updated.setId(id);
            VoucherDetail saved = repository.save(updated);
            return VoucherDetailDTO.fromEntity(saved);
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<VoucherDetailDTO> findByVoucherId(Long voucherId) {
        return repository.findAll().stream()
                .map(VoucherDetailDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
