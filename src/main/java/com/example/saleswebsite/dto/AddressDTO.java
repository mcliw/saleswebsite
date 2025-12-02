package com.example.saleswebsite.dto;

import com.example.saleswebsite.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    private Long id;
    private Long userId;
    private String city;
    private String district;
    private String ward;
    private String streetAddress;
    private Boolean isDefault;

    public static AddressDTO fromEntity(Address entity) {
        if (entity == null)
            return null;
        AddressDTO dto = new AddressDTO();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUserId());
        dto.setCity(entity.getCity());
        dto.setDistrict(entity.getDistrict());
        dto.setWard(entity.getWard());
        dto.setStreetAddress(entity.getStreetAddress());
        dto.setIsDefault(entity.getIsDefault());
        return dto;
    }

    public Address toEntity() {
        Address e = new Address();
        e.setId(this.id);
        e.setUserId(this.userId);
        e.setCity(this.city);
        e.setDistrict(this.district);
        e.setWard(this.ward);
        e.setStreetAddress(this.streetAddress);
        e.setIsDefault(this.isDefault);
        return e;
    }
}
