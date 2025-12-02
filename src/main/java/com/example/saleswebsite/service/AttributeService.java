package com.example.saleswebsite.service;

import com.example.saleswebsite.dto.AttributeDTO;
import java.util.List;

public interface AttributeService {
    List<AttributeDTO> findAll();

    AttributeDTO findById(Long id);

    AttributeDTO create(AttributeDTO dto);

    AttributeDTO update(Long id, AttributeDTO dto);

    void delete(Long id);

    AttributeDTO findByName(String name);
}
