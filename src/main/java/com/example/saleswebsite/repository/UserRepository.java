package com.example.saleswebsite.repository;

import com.example.saleswebsite.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByEmailAndIsActive(String email, Boolean isActive);

    User findByResetToken(String resetToken);

    User findByRefreshToken(String refreshToken);
}
