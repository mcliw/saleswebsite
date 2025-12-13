package com.example.saleswebsite.repository;

import com.example.saleswebsite.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Deprecated
@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    UserAccount findByUsername(String username);

    UserAccount findByEmailAndIsActive(String email, Boolean isActive);

    UserAccount findByResetToken(String resetToken);

    UserAccount findByRefreshToken(String refreshToken);
}
