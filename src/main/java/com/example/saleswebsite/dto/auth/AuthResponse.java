package com.example.saleswebsite.dto.auth;

import com.example.saleswebsite.dto.UserAccountDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private UserAccountDTO user;
    private String accessToken;
    private String refreshToken;
}
