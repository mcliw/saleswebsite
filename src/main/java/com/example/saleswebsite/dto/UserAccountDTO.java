package com.example.saleswebsite.dto;

import com.example.saleswebsite.entity.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountDTO {

    private Long id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private Boolean isActive;

    public static UserAccountDTO fromEntity(UserAccount entity) {
        if (entity == null) return null;
        UserAccountDTO dto = new UserAccountDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setIsActive(entity.getIsActive());
        return dto;
    }

    public UserAccount toEntity() {
        UserAccount e = new UserAccount();
        e.setId(this.id);
        e.setName(this.name);
        e.setUsername(this.username);
        e.setEmail(this.email);
        e.setPhone(this.phone);
        e.setIsActive(this.isActive);
        return e;
    }
}