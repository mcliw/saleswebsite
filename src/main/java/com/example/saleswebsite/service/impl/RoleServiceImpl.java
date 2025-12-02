package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.dto.RoleDTO;
import com.example.saleswebsite.entity.Role;
import com.example.saleswebsite.repository.RoleRepository;
import com.example.saleswebsite.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<RoleDTO> findAll() {
        return repository.findAll().stream().map(RoleDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public RoleDTO findById(Long id) {
        return repository.findById(id).map(RoleDTO::fromEntity).orElse(null);
    }

    @Override
    public RoleDTO create(RoleDTO dto) {
        Role saved = repository.save(dto.toEntity());
        return RoleDTO.fromEntity(saved);
    }

    @Override
    public RoleDTO update(Long id, RoleDTO dto) {
        return repository.findById(id).map(existing -> {
            Role updated = dto.toEntity();
            updated.setId(id);
            Role saved = repository.save(updated);
            return RoleDTO.fromEntity(saved);
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public RoleDTO findByName(String name) {
        return repository.findByRoleName(name) == null ? null : RoleDTO.fromEntity(repository.findByRoleName(name));
    }
}
