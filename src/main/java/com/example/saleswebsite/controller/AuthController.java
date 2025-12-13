package com.example.saleswebsite.controller;

import com.example.saleswebsite.dto.UserDTO;
import com.example.saleswebsite.dto.auth.*;
import com.example.saleswebsite.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody RegisterRequest req) {
        UserDTO dto = authService.register(req);
        if (dto == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest req) {
        AuthResponse resp = authService.login(req);
        if (resp == null)
            return ResponseEntity.status(401).build();
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshRequest req) {
        AuthResponse resp = authService.refresh(req.getRefreshToken());
        if (resp == null)
            return ResponseEntity.status(401).build();
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> me() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getName() == null)
            return ResponseEntity.status(401).build();
        UserDTO dto = authService.me(auth.getName());
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Void> forgotPassword(@RequestBody ForgotPasswordRequest req) {
        authService.initiateForgotPassword(req.getEmail());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        boolean ok = authService.resetPassword(token, newPassword);
        if (!ok)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/change-password")
    public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordRequest req) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getName() == null)
            return ResponseEntity.status(401).build();
        boolean ok = authService.changePassword(auth.getName(), req.getOldPassword(), req.getNewPassword());
        if (!ok)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }
}
