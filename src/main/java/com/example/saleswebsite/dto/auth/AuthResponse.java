package com.example.saleswebsite.dto.auth;

import com.example.saleswebsite.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private UserDTO user;
    private String accessToken;
    private String refreshToken;
}
