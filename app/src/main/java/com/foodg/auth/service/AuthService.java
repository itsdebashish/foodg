package com.foodg.auth.service;

import com.foodg.auth.dto.AuthResponse;
import com.foodg.auth.dto.LoginRequest;
import com.foodg.auth.dto.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

    boolean validateToken(String token);

    String extractToken(HttpServletRequest request);

    AuthResponse refreshToken(HttpServletRequest request);

    void logout(HttpServletRequest request);

}