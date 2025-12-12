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
        return AddressDTO.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .city(entity.getCity())
                .district(entity.getDistrict())
                .ward(entity.getWard())
                .streetAddress(entity.getStreetAddress())
                .isDefault(entity.getIsDefault())
                .build();
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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final AddressDTO dto = new AddressDTO();

        public Builder id(Long id) {
            dto.setId(id);
            return this;
        }

        public Builder userId(Long userId) {
            dto.setUserId(userId);
            return this;
        }

        public Builder city(String city) {
            dto.setCity(city);
            return this;
        }

        public Builder district(String district) {
            dto.setDistrict(district);
            return this;
        }

        public Builder ward(String ward) {
            dto.setWard(ward);
            return this;
        }

        public Builder streetAddress(String streetAddress) {
            dto.setStreetAddress(streetAddress);
            return this;
        }

        public Builder isDefault(Boolean isDefault) {
            dto.setIsDefault(isDefault);
            return this;
        }

        public AddressDTO build() {
            return dto;
        }
    }
}
