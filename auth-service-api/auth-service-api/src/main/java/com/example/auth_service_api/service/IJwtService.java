package com.example.auth_service_api.service;

import com.example.auth_service_api.dtos.TokenResponse;
import io.jsonwebtoken.Claims;

import java.util.UUID;

public interface IJwtService {
    TokenResponse generateToken(UUID userId);
    Claims getClaims(String token);
    boolean isExpired(String token);
    UUID extractedUserId(String token);
}
