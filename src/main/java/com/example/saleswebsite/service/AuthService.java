package com.example.saleswebsite.service;

import com.example.saleswebsite.dto.UserDTO;
import com.example.saleswebsite.dto.auth.AuthResponse;
import com.example.saleswebsite.dto.auth.LoginRequest;
import com.example.saleswebsite.dto.auth.RegisterRequest;

public interface AuthService {
    UserDTO register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

    AuthResponse refresh(String refreshToken);

    UserDTO me(String username);

    void initiateForgotPassword(String email);

    boolean resetPassword(String resetToken, String newPassword);

    boolean changePassword(String username, String oldPassword, String newPassword);
}
