package com.example.saleswebsite.dto;

import com.example.saleswebsite.entity.CateBrandLink;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CateBrandLinkDTO {

    private Long id;
    private Long brandId;
    private Long categoryId;

    public static CateBrandLinkDTO fromEntity(CateBrandLink e) {
        if (e == null)
            return null;
        CateBrandLinkDTO dto = new CateBrandLinkDTO();
        dto.setId(e.getId());
        dto.setBrandId(e.getBrandId());
        dto.setCategoryId(e.getCategoryId());
        return dto;
    }

    public CateBrandLink toEntity() {
        CateBrandLink e = new CateBrandLink();
        e.setId(this.id);
        e.setBrandId(this.brandId);
        e.setCategoryId(this.categoryId);
        return e;
    }
}
