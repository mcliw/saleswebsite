package com.example.saleswebsite.service;

import com.example.saleswebsite.dto.VoucherDTO;

import java.util.List;

public interface VoucherService {
    List<VoucherDTO> findAll();

    VoucherDTO findById(Long id);

    VoucherDTO create(VoucherDTO dto);

    VoucherDTO update(Long id, VoucherDTO dto);

    void delete(Long id);

    VoucherDTO findByCode(String code);
}
