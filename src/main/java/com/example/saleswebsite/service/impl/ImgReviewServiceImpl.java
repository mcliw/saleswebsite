package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.dto.ImgReviewDTO;
import com.example.saleswebsite.entity.ImgReview;
import com.example.saleswebsite.repository.ImgReviewRepository;
import com.example.saleswebsite.service.ImgReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImgReviewServiceImpl implements ImgReviewService {

    private final ImgReviewRepository imgReviewRepository;

    public ImgReviewServiceImpl(ImgReviewRepository imgReviewRepository) {
        this.imgReviewRepository = imgReviewRepository;
    }

    @Override
    public List<ImgReviewDTO> findAll() {
        return imgReviewRepository.findAll().stream().map(ImgReviewDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public ImgReviewDTO findById(Long id) {
        return imgReviewRepository.findById(id).map(ImgReviewDTO::fromEntity).orElse(null);
    }

    @Override
    public ImgReviewDTO create(ImgReviewDTO dto) {
        ImgReview saved = imgReviewRepository.save(dto.toEntity());
        return ImgReviewDTO.fromEntity(saved);
    }

    @Override
    public ImgReviewDTO update(Long id, ImgReviewDTO dto) {
        return imgReviewRepository.findById(id).map(existing -> {
            ImgReview updated = dto.toEntity();
            updated.setId(id);
            ImgReview saved = imgReviewRepository.save(updated);
            return ImgReviewDTO.fromEntity(saved);
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        imgReviewRepository.deleteById(id);
    }

    @Override
    public List<ImgReviewDTO> findByReviewId(Long reviewId) {
        return imgReviewRepository.findAll().stream()
                .map(ImgReviewDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
