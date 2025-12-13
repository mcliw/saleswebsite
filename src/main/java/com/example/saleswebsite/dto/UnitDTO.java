package com.example.saleswebsite.dto;

import com.example.saleswebsite.entity.Unit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnitDTO {
    private Long id;
    private String symbol;

    public static UnitDTO fromEntity(Unit e) {
        if (e == null)
            return null;
        UnitDTO dto = new UnitDTO();
        dto.setId(e.getId());
        dto.setSymbol(e.getSymbol());
        return dto;
    }

    public Unit toEntity() {
        Unit e = new Unit();
        e.setId(this.id);
        e.setSymbol(this.symbol);
        return e;
    }
}
