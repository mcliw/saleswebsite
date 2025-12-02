package com.example.saleswebsite.controller;

import com.example.saleswebsite.dto.ImgReviewDTO;
import com.example.saleswebsite.service.ImgReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/img-reviews")
public class ImgReviewController {

    private final ImgReviewService imgReviewService;

    public ImgReviewController(ImgReviewService imgReviewService) {
        this.imgReviewService = imgReviewService;
    }

    @GetMapping
    public ResponseEntity<List<ImgReviewDTO>> getAll() {
        return ResponseEntity.ok(imgReviewService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImgReviewDTO> getById(@PathVariable Long id) {
        ImgReviewDTO dto = imgReviewService.findById(id);
        if (dto == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ImgReviewDTO> create(@RequestBody ImgReviewDTO dto) {
        return ResponseEntity.ok(imgReviewService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImgReviewDTO> update(@PathVariable Long id, @RequestBody ImgReviewDTO dto) {
        ImgReviewDTO updated = imgReviewService.update(id, dto);
        if (updated == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        imgReviewService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-review/{reviewId}")
    public ResponseEntity<List<ImgReviewDTO>> byReview(@PathVariable Long reviewId) {
        return ResponseEntity.ok(imgReviewService.findByReviewId(reviewId));
    }
}
