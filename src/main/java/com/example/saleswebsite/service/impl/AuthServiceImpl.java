package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.dto.UserAccountDTO;
import com.example.saleswebsite.dto.auth.AuthResponse;
import com.example.saleswebsite.dto.auth.LoginRequest;
import com.example.saleswebsite.dto.auth.RegisterRequest;
import com.example.saleswebsite.entity.UserAccount;
import com.example.saleswebsite.repository.UserAccountRepository;
import com.example.saleswebsite.security.JwtUtil;
import com.example.saleswebsite.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserAccountRepository repository;
    private final PasswordEncoder passwordEncoder;
    // JwtUtil is accessed through the singleton accessor JwtUtil.getInstance()

    public AuthServiceImpl(UserAccountRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserAccountDTO register(RegisterRequest request) {
        UserAccount existing = repository.findByUsername(request.getUsername());
        if (existing != null)
            return null;
        UserAccount u = new UserAccount();
        u.setUsername(request.getUsername());
        u.setName(request.getName());
        u.setEmail(request.getEmail());
        u.setPhone(request.getPhone());
        u.setIsActive(true);
        u.setRole(request.getRole() == null ? "USER" : request.getRole());
        u.setPassword(passwordEncoder.encode(request.getPassword()));
        UserAccount saved = repository.save(u);
        return UserAccountDTO.fromEntity(saved);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        UserAccount user = repository.findByUsername(request.getUsername());
        if (user == null)
            return null;
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            return null;
        String access = JwtUtil.getInstance().generateAccessToken(user.getUsername(), user.getRole());
        String refresh = JwtUtil.getInstance().generateRefreshToken(user.getUsername());
        user.setRefreshToken(refresh);
        repository.save(user);
        return new AuthResponse(UserAccountDTO.fromEntity(user), access, refresh);
    }

    @Override
    public AuthResponse refresh(String refreshToken) {
        if (refreshToken == null)
            return null;
        try {
            String username = JwtUtil.getInstance().getUsernameFromToken(refreshToken);
            UserAccount user = repository.findByUsername(username);
            if (user == null)
                return null;
            if (refreshToken.equals(user.getRefreshToken())) {
                String access = JwtUtil.getInstance().generateAccessToken(user.getUsername(), user.getRole());
                String newRefresh = JwtUtil.getInstance().generateRefreshToken(user.getUsername());
                user.setRefreshToken(newRefresh);
                repository.save(user);
                return new AuthResponse(UserAccountDTO.fromEntity(user), access, newRefresh);
            }
        } catch (Exception ex) {
            return null;
        }
        return null;
    }

    @Override
    public UserAccountDTO me(String username) {
        UserAccount u = repository.findByUsername(username);
        return UserAccountDTO.fromEntity(u);
    }

    @Override
    public void initiateForgotPassword(String email) {
        UserAccount u = repository.findByEmailAndIsActive(email, true);
        if (u == null)
            return;
        String token = UUID.randomUUID().toString();
        u.setResetToken(token);
        repository.save(u);
        // In real app send email containing token. For demo, we just store token.
    }

    @Override
    public boolean resetPassword(String resetToken, String newPassword) {
        UserAccount u = repository.findByResetToken(resetToken);
        if (u == null)
            return false;
        u.setPassword(passwordEncoder.encode(newPassword));
        u.setResetToken(null);
        repository.save(u);
        return true;
    }

    @Override
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        UserAccount u = repository.findByUsername(username);
        if (u == null)
            return false;
        if (!passwordEncoder.matches(oldPassword, u.getPassword()))
            return false;
        u.setPassword(passwordEncoder.encode(newPassword));
        repository.save(u);
        return true;
    }
}
