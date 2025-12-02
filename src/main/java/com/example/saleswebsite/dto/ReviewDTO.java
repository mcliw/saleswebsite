package com.example.saleswebsite.dto;

import com.example.saleswebsite.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

    private Long id;
    private String content;
    private Integer rate;
    private Long imgReviewId;
    private Long productId;

    public static ReviewDTO fromEntity(Review entity) {
        if (entity == null)
            return null;
        ReviewDTO dto = new ReviewDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setRate(entity.getRate());
        dto.setImgReviewId(entity.getImgReviewId());
        dto.setProductId(entity.getProductId());
        return dto;
    }

    public Review toEntity() {
        Review e = new Review();
        e.setId(this.id);
        e.setContent(this.content);
        e.setRate(this.rate);
        e.setImgReviewId(this.imgReviewId);
        e.setProductId(this.productId);
        return e;
    }
}
