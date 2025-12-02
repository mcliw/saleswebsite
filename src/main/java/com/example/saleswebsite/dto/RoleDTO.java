package com.example.saleswebsite.dto;

import com.example.saleswebsite.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {

    private Long id;
    private Long userId;
    private String roleName;

    public static RoleDTO fromEntity(Role entity) {
        if (entity == null)
            return null;
        RoleDTO dto = new RoleDTO();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUserId());
        dto.setRoleName(entity.getRoleName());
        return dto;
    }

    public Role toEntity() {
        Role e = new Role();
        e.setId(this.id);
        e.setUserId(this.userId);
        e.setRoleName(this.roleName);
        return e;
    }
}
