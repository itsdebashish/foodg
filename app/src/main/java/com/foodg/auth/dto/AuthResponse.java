package com.foodg.auth.dto;

import com.foodg.common.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse{
    private Long userId;
    private String name;
    private String email;
    private Role role;
    private String token;
}