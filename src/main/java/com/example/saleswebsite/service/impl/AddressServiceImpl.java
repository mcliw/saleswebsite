package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.dto.AddressDTO;
import com.example.saleswebsite.entity.Address;
import com.example.saleswebsite.repository.AddressRepository;
import com.example.saleswebsite.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<AddressDTO> findAll() {
        return addressRepository.findAll().stream().map(AddressDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public AddressDTO findById(Long id) {
        return addressRepository.findById(id).map(AddressDTO::fromEntity).orElse(null);
    }

    @Override
    public AddressDTO create(AddressDTO dto) {
        Address e = dto.toEntity();
        Address saved = addressRepository.save(e);
        return AddressDTO.fromEntity(saved);
    }

    @Override
    public AddressDTO update(Long id, AddressDTO dto) {
        return addressRepository.findById(id).map(existing -> {
            Address updated = dto.toEntity();
            updated.setId(id);
            Address saved = addressRepository.save(updated);
            return AddressDTO.fromEntity(saved);
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public List<AddressDTO> findByUserId(Long userId) {
        return addressRepository.findByUserId(userId).stream().map(AddressDTO::fromEntity).collect(Collectors.toList());
    }
}
