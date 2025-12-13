package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.dto.ImgReviewDTO;
import com.example.saleswebsite.entity.ImgReview;
import com.example.saleswebsite.repository.ImgReviewRepository;
import com.example.saleswebsite.service.ImgReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.saleswebsite.exception.ApiException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImgReviewServiceImpl implements ImgReviewService {
    private static final Logger log = LoggerFactory.getLogger(ImgReviewServiceImpl.class);

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
    @Transactional
    public ImgReviewDTO create(ImgReviewDTO dto) {
        log.info("Creating review image {}", dto.getImage());
        ImgReview saved = imgReviewRepository.save(dto.toEntity());
        return ImgReviewDTO.fromEntity(saved);
    }

    @Override
    @Transactional
    public ImgReviewDTO update(Long id, ImgReviewDTO dto) {
        return imgReviewRepository.findById(id).map(existing -> {
            log.info("Updating img review {}", id);
            ImgReview updated = dto.toEntity();
            updated.setId(id);
            ImgReview saved = imgReviewRepository.save(updated);
            return ImgReviewDTO.fromEntity(saved);
        }).orElseThrow(() -> new ApiException("ImgReview not found"));
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting img review {}", id);
        imgReviewRepository.deleteById(id);
    }

    @Override
    public List<ImgReviewDTO> findByReviewId(Long reviewId) {
        return imgReviewRepository.findAll().stream()
                .map(ImgReviewDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
