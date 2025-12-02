package com.example.saleswebsite.dto;

import com.example.saleswebsite.entity.Voucher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoucherDTO {

    private Long id;
    private Long voucherDetailId;
    private Double discountValue;

    public static VoucherDTO fromEntity(Voucher entity) {
        if (entity == null)
            return null;
        VoucherDTO dto = new VoucherDTO();
        dto.setId(entity.getId());
        dto.setVoucherDetailId(entity.getVoucherDetailId());
        dto.setDiscountValue(entity.getDiscountValue());
        return dto;
    }

    public Voucher toEntity() {
        Voucher e = new Voucher();
        e.setId(this.id);
        e.setVoucherDetailId(this.voucherDetailId);
        e.setDiscountValue(this.discountValue);
        return e;
    }
}
