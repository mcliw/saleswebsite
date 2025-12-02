package com.example.saleswebsite.repository;

import com.example.saleswebsite.entity.ImgReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImgReviewRepository extends JpaRepository<ImgReview, Long> {
    ImgReview findByImage(String image);
}
