package com.foodg.auth.controller;

import com.foodg.auth.dto.AuthResponse;
import com.foodg.auth.dto.LoginRequest;
import com.foodg.auth.dto.RegisterRequest;
import com.foodg.auth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @Valid @RequestBody RegisterRequest request) {

        AuthResponse response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request) {

        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(HttpServletRequest request) {

        String token = authService.extractToken(request);

        if (authService.validateToken(token)) {
            return ResponseEntity.ok("Token is valid");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Invalid or Expired Token");
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthResponse> refreshToken(
            HttpServletRequest request) {

        AuthResponse response = authService.refreshToken(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            HttpServletRequest request) {

        authService.logout(request);
        return ResponseEntity.ok("Logged out successfully");
    }

}