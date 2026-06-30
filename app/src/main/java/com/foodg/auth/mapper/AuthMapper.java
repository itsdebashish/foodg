package com.foodg.auth.mapper;


import com.foodg.auth.dto.AuthResponse;
import com.foodg.auth.dto.RegisterRequest;
import com.foodg.common.enums.Role;
import com.foodg.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {
    public User toUser(RegisterRequest user) {
        return User.builder().name(user.getName())
                .email(user.getEmail())
                .role(user.getRole() != null ? user.getRole() : Role.CUSTOMER).build();
    }

    public AuthResponse fromUser(User savedUser) {
        return AuthResponse.builder().userId(savedUser.getId())
                .email(savedUser.getEmail())
                .name(savedUser.getName())
                .role(savedUser.getRole())
                .build();
    }
}