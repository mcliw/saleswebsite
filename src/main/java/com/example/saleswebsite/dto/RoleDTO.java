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
    private String name;

    public static RoleDTO fromEntity(Role entity) {
        if (entity == null)
            return null;
        RoleDTO dto = new RoleDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getRoleName());
        return dto;
    }

    public Role toEntity() {
        Role e = new Role();
        e.setId(this.id);
        e.setRoleName(this.name);
        return e;
    }
}
