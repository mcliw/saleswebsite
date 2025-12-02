package com.example.saleswebsite.service;

import com.example.saleswebsite.dto.RoleDTO;

import java.util.List;

public interface RoleService {
    List<RoleDTO> findAll();

    RoleDTO findById(Long id);

    RoleDTO create(RoleDTO dto);

    RoleDTO update(Long id, RoleDTO dto);

    void delete(Long id);

    RoleDTO findByName(String name);
}
