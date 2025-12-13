package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.dto.ReviewDTO;
import com.example.saleswebsite.entity.Review;
import com.example.saleswebsite.repository.ReviewRepository;
import com.example.saleswebsite.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.saleswebsite.exception.ApiException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    private static final Logger log = LoggerFactory.getLogger(ReviewServiceImpl.class);

    private final ReviewRepository repository;

    public ReviewServiceImpl(ReviewRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ReviewDTO> findAll() {
        return repository.findAll().stream().map(ReviewDTO::fromEntity).collect(Collectors.toList());
    }

    @Override
    public ReviewDTO findById(Long id) {
        return repository.findById(id).map(ReviewDTO::fromEntity).orElse(null);
    }

    @Override
    @Transactional
    public ReviewDTO create(ReviewDTO dto) {
        log.info("Creating review for product {}", dto.getProductId());
        Review saved = repository.save(dto.toEntity());
        return ReviewDTO.fromEntity(saved);
    }

    @Override
    @Transactional
    public ReviewDTO update(Long id, ReviewDTO dto) {
        return repository.findById(id).map(existing -> {
            log.info("Updating review {}", id);
            Review updated = dto.toEntity();
            updated.setId(id);
            Review saved = repository.save(updated);
            return ReviewDTO.fromEntity(saved);
        }).orElseThrow(() -> new ApiException("Review not found"));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting review {}", id);
        repository.deleteById(id);
    }

    @Override
    public List<ReviewDTO> findByProductId(Long productId) {
        return repository.findByProductId(productId).stream().map(ReviewDTO::fromEntity).collect(Collectors.toList());
    }
}
