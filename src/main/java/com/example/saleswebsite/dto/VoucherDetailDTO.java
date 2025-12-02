package com.example.saleswebsite.dto;

import com.example.saleswebsite.entity.VoucherDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoucherDetailDTO {

    private Long id;
    private String code;
    private String discountType;
    private Boolean isActive;

    public static VoucherDetailDTO fromEntity(VoucherDetail entity) {
        if (entity == null)
            return null;
        VoucherDetailDTO dto = new VoucherDetailDTO();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setDiscountType(entity.getDiscountType());
        dto.setIsActive(entity.getIsActive());
        return dto;
    }

    public VoucherDetail toEntity() {
        VoucherDetail e = new VoucherDetail();
        e.setId(this.id);
        e.setCode(this.code);
        e.setDiscountType(this.discountType);
        e.setIsActive(this.isActive);
        return e;
    }
}
