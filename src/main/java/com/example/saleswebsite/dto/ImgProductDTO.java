package com.example.saleswebsite.dto;

import com.example.saleswebsite.entity.ImgProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImgProductDTO {

    private Long id;
    private String image;

    public static ImgProductDTO fromEntity(ImgProduct entity) {
        if (entity == null)
            return null;
        ImgProductDTO dto = new ImgProductDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        return dto;
    }

    public ImgProduct toEntity() {
        ImgProduct e = new ImgProduct();
        e.setId(this.id);
        e.setImage(this.image);
        return e;
    }
}
