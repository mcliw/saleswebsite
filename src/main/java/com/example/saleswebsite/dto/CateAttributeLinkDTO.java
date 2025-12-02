package com.example.saleswebsite.dto;

import com.example.saleswebsite.entity.CateAttributeLink;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CateAttributeLinkDTO {

    private Long id;
    private Long attributeId;
    private Long categoryId;

    public static CateAttributeLinkDTO fromEntity(CateAttributeLink entity) {
        if (entity == null)
            return null;
        CateAttributeLinkDTO dto = new CateAttributeLinkDTO();
        dto.setId(entity.getId());
        dto.setAttributeId(entity.getAttributeId());
        dto.setCategoryId(entity.getCategoryId());
        return dto;
    }

    public CateAttributeLink toEntity() {
        CateAttributeLink e = new CateAttributeLink();
        e.setId(this.id);
        e.setAttributeId(this.attributeId);
        e.setCategoryId(this.categoryId);
        return e;
    }
}
