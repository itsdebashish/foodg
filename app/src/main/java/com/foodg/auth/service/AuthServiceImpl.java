package com.foodg.auth.service;

import com.foodg.auth.dto.AuthResponse;
import com.foodg.auth.dto.LoginRequest;
import com.foodg.auth.dto.RegisterRequest;
import com.foodg.auth.mapper.AuthMapper;
import com.foodg.common.enums.Role;
import com.foodg.security.jwt.JwtService;
import com.foodg.user.entity.User;
import com.foodg.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthMapper authMapper;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = authMapper.toUser(request);

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User savedUser = userRepository.save(user);

        String token = jwtService.generateToken(savedUser);

        AuthResponse response = authMapper.fromUser(savedUser);
        response.setToken(token);

        return response;
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtService.generateToken(user);

        AuthResponse response = authMapper.fromUser(user);
        response.setToken(token);

        return response;
    }

    @Override
    public boolean validateToken(String token) {

        if (token == null) {
            return false;
        }

        String username = jwtService.extractUserName(token);

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return jwtService.isTokenExpired(token);
    }


    public String extractToken(HttpServletRequest request) {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }

        return null;
    }

    @Override
    public AuthResponse refreshToken(HttpServletRequest request) {

        String token = extractToken(request);

        if (!validateToken(token)) {
            throw new RuntimeException("Invalid Token");
        }

        String username = jwtService.extractUserName(token);

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String newToken = jwtService.generateToken(user);

        AuthResponse response = authMapper.fromUser(user);
        response.setToken(newToken);

        return response;
    }

    @Override
    public void logout(HttpServletRequest request) {
    }
}