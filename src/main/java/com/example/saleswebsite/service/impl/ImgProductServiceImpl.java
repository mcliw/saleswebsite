package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.dto.ImgProductDTO;
import com.example.saleswebsite.entity.ImgProduct;
import com.example.saleswebsite.repository.ImgProductRepository;
import com.example.saleswebsite.service.ImgProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImgProductServiceImpl implements ImgProductService {

    private final ImgProductRepository imgProductRepository;

    public ImgProductServiceImpl(ImgProductRepository imgProductRepository) {
        this.imgProductRepository = imgProductRepository;
    }

    @Override
    public List<ImgProductDTO> findAll() {
        return imgProductRepository.findAll().stream().map(ImgProductDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public ImgProductDTO findById(Long id) {
        return imgProductRepository.findById(id).map(ImgProductDTO::fromEntity).orElse(null);
    }

    @Override
    public ImgProductDTO create(ImgProductDTO dto) {
        ImgProduct saved = imgProductRepository.save(dto.toEntity());
        return ImgProductDTO.fromEntity(saved);
    }

    @Override
    public ImgProductDTO update(Long id, ImgProductDTO dto) {
        return imgProductRepository.findById(id).map(existing -> {
            ImgProduct updated = dto.toEntity();
            updated.setId(id);
            ImgProduct saved = imgProductRepository.save(updated);
            return ImgProductDTO.fromEntity(saved);
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        imgProductRepository.deleteById(id);
    }

    @Override
    public List<ImgProductDTO> findByProductId(Long productId) {
        return imgProductRepository.findAll().stream()
                .map(ImgProductDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
