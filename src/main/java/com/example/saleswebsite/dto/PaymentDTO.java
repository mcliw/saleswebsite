package com.example.saleswebsite.dto;

import com.example.saleswebsite.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {

    private Long id;
    private Double amount;

    public static PaymentDTO fromEntity(Payment entity) {
        if (entity == null)
            return null;
        PaymentDTO dto = new PaymentDTO();
        dto.setId(entity.getId());
        dto.setAmount(entity.getAmount());
        return dto;
    }

    public Payment toEntity() {
        Payment e = new Payment();
        e.setId(this.id);
        e.setAmount(this.amount);
        return e;
    }
}
