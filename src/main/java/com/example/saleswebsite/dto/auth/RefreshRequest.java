package com.example.saleswebsite.dto.auth;

import lombok.Data;

@Data
public class RefreshRequest {
    private String refreshToken;
}
