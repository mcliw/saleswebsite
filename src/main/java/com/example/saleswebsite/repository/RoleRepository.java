package com.example.saleswebsite.repository;

import com.example.saleswebsite.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByUserId(Long userId);

    Role findByRoleName(String roleName);
}
