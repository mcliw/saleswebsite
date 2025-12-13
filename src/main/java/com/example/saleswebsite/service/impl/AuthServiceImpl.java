package com.example.saleswebsite.service.impl;

import com.example.saleswebsite.dto.UserDTO;
import com.example.saleswebsite.dto.auth.AuthResponse;
import com.example.saleswebsite.dto.auth.LoginRequest;
import com.example.saleswebsite.dto.auth.RegisterRequest;
import com.example.saleswebsite.entity.User;
import com.example.saleswebsite.entity.Role;
import com.example.saleswebsite.repository.UserRepository;
import com.example.saleswebsite.repository.RoleRepository;
import com.example.saleswebsite.security.JwtUtil;
import com.example.saleswebsite.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    // JwtUtil is accessed through the singleton accessor JwtUtil.getInstance()

    public AuthServiceImpl(UserRepository repository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO register(RegisterRequest request) {
        User existing = repository.findByUsername(request.getUsername());
        if (existing != null)
            return null;
        User u = new User();
        u.setUsername(request.getUsername());
        u.setName(request.getName());
        u.setEmail(request.getEmail());
        u.setPhone(request.getPhone());
        u.setIsActive(true);
        // resolve role by name
        String rname = request.getRole() == null ? "User" : request.getRole();
        Role roleEntity = roleRepository.findByRoleName(rname);
        if (roleEntity != null) {
            u.setRoleId(roleEntity.getId());
        }
        u.setPassword(passwordEncoder.encode(request.getPassword()));
        User saved = repository.save(u);
        return UserDTO.fromEntity(saved);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        User user = repository.findByUsername(request.getUsername());
        if (user == null)
            return null;
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            return null;
        String roleName = "User";
        if (user.getRoleId() != null) {
            var r = roleRepository.findById(user.getRoleId());
            if (r.isPresent() && r.get().getRoleName() != null) {
                roleName = r.get().getRoleName();
            }
        }
        String access = JwtUtil.getInstance().generateAccessToken(user.getUsername(), roleName);
        String refresh = JwtUtil.getInstance().generateRefreshToken(user.getUsername());
        user.setRefreshToken(refresh);
        repository.save(user);
        return new AuthResponse(UserDTO.fromEntity(user), access, refresh);
    }

    @Override
    public AuthResponse refresh(String refreshToken) {
        if (refreshToken == null)
            return null;
        try {
            String username = JwtUtil.getInstance().getUsernameFromToken(refreshToken);
            User user = repository.findByUsername(username);
            if (user == null)
                return null;
            if (refreshToken.equals(user.getRefreshToken())) {
                String roleName = "User";
                if (user.getRoleId() != null) {
                    var r = roleRepository.findById(user.getRoleId());
                    if (r.isPresent() && r.get().getRoleName() != null) {
                        roleName = r.get().getRoleName();
                    }
                }
                String access = JwtUtil.getInstance().generateAccessToken(user.getUsername(), roleName);
                String newRefresh = JwtUtil.getInstance().generateRefreshToken(user.getUsername());
                user.setRefreshToken(newRefresh);
                repository.save(user);
                return new AuthResponse(UserDTO.fromEntity(user), access, newRefresh);
            }
        } catch (Exception ex) {
            return null;
        }
        return null;
    }

    @Override
    public UserDTO me(String username) {
        User u = repository.findByUsername(username);
        return UserDTO.fromEntity(u);
    }

    @Override
    public void initiateForgotPassword(String email) {
        User u = repository.findByEmailAndIsActive(email, true);
        if (u == null)
            return;
        String token = UUID.randomUUID().toString();
        u.setResetToken(token);
        repository.save(u);
        // In real app send email containing token. For demo, we just store token.
    }

    @Override
    public boolean resetPassword(String resetToken, String newPassword) {
        User u = repository.findByResetToken(resetToken);
        if (u == null)
            return false;
        u.setPassword(passwordEncoder.encode(newPassword));
        u.setResetToken(null);
        repository.save(u);
        return true;
    }

    @Override
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        User u = repository.findByUsername(username);
        if (u == null)
            return false;
        if (!passwordEncoder.matches(oldPassword, u.getPassword()))
            return false;
        u.setPassword(passwordEncoder.encode(newPassword));
        repository.save(u);
        return true;
    }
}
