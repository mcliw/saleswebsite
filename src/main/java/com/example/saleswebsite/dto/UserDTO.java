package com.example.saleswebsite.dto;

import com.example.saleswebsite.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private Boolean isActive;
    private Long roleId;

    public static UserDTO fromEntity(User entity) {
        if (entity == null)
            return null;
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setIsActive(entity.getIsActive());
        dto.setRoleId(entity.getRoleId());
        return dto;
    }

    public User toEntity() {
        User e = new User();
        e.setId(this.id);
        e.setName(this.name);
        e.setUsername(this.username);
        e.setEmail(this.email);
        e.setPhone(this.phone);
        e.setIsActive(this.isActive);
        e.setRoleId(this.roleId);
        return e;
    }
}