package com.example.saleswebsite.repository;

import com.example.saleswebsite.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProductId(Long productId);

    List<Review> findByImgReviewId(Long imgReviewId);

    List<Review> findByRate(Integer rate);
}
