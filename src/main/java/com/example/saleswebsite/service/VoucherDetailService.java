package com.example.saleswebsite.service;

import com.example.saleswebsite.dto.VoucherDetailDTO;

import java.util.List;

public interface VoucherDetailService {
    List<VoucherDetailDTO> findAll();

    VoucherDetailDTO findById(Long id);

    VoucherDetailDTO create(VoucherDetailDTO dto);

    VoucherDetailDTO update(Long id, VoucherDetailDTO dto);

    void delete(Long id);

    List<VoucherDetailDTO> findByVoucherId(Long voucherId);
}
