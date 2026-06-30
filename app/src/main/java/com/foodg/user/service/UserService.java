package com.foodg.user.service;


import com.foodg.user.dto.ChangePasswordRequest;
import com.foodg.user.dto.UpdateUserRequest;
import com.foodg.user.dto.UserResponse;

public interface UserService {

    public UserResponse getProfile();

    UserResponse updateUser(UpdateUserRequest request);

    UserResponse getUserById(Long id);

    void deleteUser(Long id);

    void changePassword(ChangePasswordRequest request);
}