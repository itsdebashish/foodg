package com.foodg.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {


    @NotBlank(message = "Name is required")
    private String name;


    @Email(message = "Invalid email format")
    private String email;
}