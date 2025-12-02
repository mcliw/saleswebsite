package com.example.saleswebsite.service;

import com.example.saleswebsite.dto.AddressDTO;
import java.util.List;

public interface AddressService {
    List<AddressDTO> findAll();

    AddressDTO findById(Long id);

    AddressDTO create(AddressDTO dto);

    AddressDTO update(Long id, AddressDTO dto);

    void delete(Long id);

    List<AddressDTO> findByUserId(Long userId);
}
