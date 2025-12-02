package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.dto.VoucherDTO;
import com.example.saleswebsite.entity.Voucher;
import com.example.saleswebsite.repository.VoucherRepository;
import com.example.saleswebsite.service.VoucherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoucherServiceImpl implements VoucherService {

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
    public VoucherDTO create(VoucherDTO dto) {
        Voucher saved = repository.save(dto.toEntity());
        return VoucherDTO.fromEntity(saved);
    }

    @Override
    public VoucherDTO update(Long id, VoucherDTO dto) {
        return repository.findById(id).map(existing -> {
            Voucher updated = dto.toEntity();
            updated.setId(id);
            Voucher saved = repository.save(updated);
            return VoucherDTO.fromEntity(saved);
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public VoucherDTO findByCode(String code) {
        return null;
    }
}
