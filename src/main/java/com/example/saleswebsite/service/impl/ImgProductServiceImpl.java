package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.dto.ImgProductDTO;
import com.example.saleswebsite.entity.ImgProduct;
import com.example.saleswebsite.repository.ImgProductRepository;
import com.example.saleswebsite.service.ImgProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.saleswebsite.exception.ApiException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImgProductServiceImpl implements ImgProductService {
    private static final Logger log = LoggerFactory.getLogger(ImgProductServiceImpl.class);

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
    @Transactional
    public ImgProductDTO create(ImgProductDTO dto) {
        log.info("Creating product image {}", dto.getImage());
        ImgProduct saved = imgProductRepository.save(dto.toEntity());
        return ImgProductDTO.fromEntity(saved);
    }

    @Override
    @Transactional
    public ImgProductDTO update(Long id, ImgProductDTO dto) {
        return imgProductRepository.findById(id).map(existing -> {
            log.info("Updating img product {}", id);
            ImgProduct updated = dto.toEntity();
            updated.setId(id);
            ImgProduct saved = imgProductRepository.save(updated);
            return ImgProductDTO.fromEntity(saved);
        }).orElseThrow(() -> new ApiException("ImgProduct not found"));
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting img product {}", id);
        imgProductRepository.deleteById(id);
    }

    @Override
    public List<ImgProductDTO> findByProductId(Long productId) {
        return imgProductRepository.findAll().stream()
                .map(ImgProductDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
