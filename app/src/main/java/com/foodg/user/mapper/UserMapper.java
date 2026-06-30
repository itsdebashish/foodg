package com.foodg.user.mapper;


import com.foodg.user.dto.UserResponse;
import com.foodg.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {


    public UserResponse toResponse(User user) {

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .active(user.getActive())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}