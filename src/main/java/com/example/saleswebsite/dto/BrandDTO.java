package com.example.saleswebsite.dto;

import com.example.saleswebsite.entity.Brand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandDTO {

    private Long id;
    private String name;
    private String slug;
    private String logo;

    public static BrandDTO fromEntity(Brand entity) {
        if (entity == null)
            return null;
        BrandDTO dto = new BrandDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSlug(entity.getSlug());
        dto.setLogo(entity.getLogo());
        return dto;
    }

    public Brand toEntity() {
        Brand e = new Brand();
        e.setId(this.id);
        e.setName(this.name);
        e.setSlug(this.slug);
        e.setLogo(this.logo);
        return e;
    }
}
