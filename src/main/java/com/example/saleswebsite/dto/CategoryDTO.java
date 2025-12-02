package com.example.saleswebsite.dto;

import com.example.saleswebsite.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    private Long id;
    private String name;
    private String slug;
    private Long parentId;
    private String img;

    public static CategoryDTO fromEntity(Category entity) {
        if (entity == null)
            return null;
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSlug(entity.getSlug());
        dto.setParentId(entity.getParentId());
        dto.setImg(entity.getImg());
        return dto;
    }

    public Category toEntity() {
        Category e = new Category();
        e.setId(this.id);
        e.setName(this.name);
        e.setSlug(this.slug);
        e.setParentId(this.parentId);
        e.setImg(this.img);
        return e;
    }
}
