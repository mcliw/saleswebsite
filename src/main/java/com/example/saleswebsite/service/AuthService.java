package com.example.saleswebsite.service;

import com.example.saleswebsite.dto.UserAccountDTO;
import com.example.saleswebsite.dto.auth.AuthResponse;
import com.example.saleswebsite.dto.auth.LoginRequest;
import com.example.saleswebsite.dto.auth.RegisterRequest;

public interface AuthService {
    UserAccountDTO register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

    AuthResponse refresh(String refreshToken);

    UserAccountDTO me(String username);

    void initiateForgotPassword(String email);

    boolean resetPassword(String resetToken, String newPassword);

    boolean changePassword(String username, String oldPassword, String newPassword);
}
