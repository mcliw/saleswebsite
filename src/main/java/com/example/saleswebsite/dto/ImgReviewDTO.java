package com.example.saleswebsite.dto;

import com.example.saleswebsite.entity.ImgReview;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImgReviewDTO {

    private Long id;
    private String image;

    public static ImgReviewDTO fromEntity(ImgReview entity) {
        if (entity == null)
            return null;
        ImgReviewDTO dto = new ImgReviewDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        return dto;
    }

    public ImgReview toEntity() {
        ImgReview e = new ImgReview();
        e.setId(this.id);
        e.setImage(this.image);
        return e;
    }
}
