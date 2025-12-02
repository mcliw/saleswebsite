package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.dto.ReviewDTO;
import com.example.saleswebsite.entity.Review;
import com.example.saleswebsite.repository.ReviewRepository;
import com.example.saleswebsite.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

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
    public ReviewDTO create(ReviewDTO dto) {
        Review saved = repository.save(dto.toEntity());
        return ReviewDTO.fromEntity(saved);
    }

    @Override
    public ReviewDTO update(Long id, ReviewDTO dto) {
        return repository.findById(id).map(existing -> {
            Review updated = dto.toEntity();
            updated.setId(id);
            Review saved = repository.save(updated);
            return ReviewDTO.fromEntity(saved);
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<ReviewDTO> findByProductId(Long productId) {
        return repository.findByProductId(productId).stream().map(ReviewDTO::fromEntity).collect(Collectors.toList());
    }
}
