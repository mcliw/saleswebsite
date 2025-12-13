package com.example.saleswebsite.dto;

import com.example.saleswebsite.entity.Attribute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttributeDTO {

    private Long id;
    private String name;
    private Long unitId;
    private String dataType;
    private String inputType;

    public static AttributeDTO fromEntity(Attribute entity) {
        if (entity == null)
            return null;
        AttributeDTO dto = new AttributeDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setUnitId(entity.getUnitId());
        dto.setDataType(entity.getDataType());
        dto.setInputType(entity.getInputType());
        return dto;
    }

    public Attribute toEntity() {
        Attribute e = new Attribute();
        e.setId(this.id);
        e.setName(this.name);
        e.setUnitId(this.unitId);
        e.setDataType(this.dataType);
        e.setInputType(this.inputType);
        return e;
    }
}
