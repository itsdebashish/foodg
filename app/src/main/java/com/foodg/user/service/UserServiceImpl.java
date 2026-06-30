package com.foodg.user.service;


import com.foodg.user.dto.ChangePasswordRequest;
import com.foodg.user.dto.UpdateUserRequest;
import com.foodg.user.dto.UserResponse;
import com.foodg.user.entity.User;
import com.foodg.user.mapper.UserMapper;
import com.foodg.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    private User getCurrentUser() {
        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email = authentication.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }


    @Override
    public UserResponse getProfile() {
        User user = getCurrentUser();
        return userMapper.toResponse(user);
    }


    @Override
    public UserResponse updateUser(UpdateUserRequest request) {
        User user = getCurrentUser();

        user.setName(request.getName());


        if (request.getEmail() != null
                && !request.getEmail().equals(user.getEmail())) {


            if (userRepository.existsByEmail(request.getEmail())) {
                throw new RuntimeException("Email already exists");
            }


            user.setEmail(request.getEmail());
        }


        User savedUser = userRepository.save(user);


        return userMapper.toResponse(savedUser);
    }


    @Override
    public UserResponse getUserById(Long id) {


        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));


        return userMapper.toResponse(user);
    }


    @Override
    public void deleteUser(Long id) {


        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        userRepository.delete(user);
    }


    @Override
    public void changePassword(ChangePasswordRequest request) {


        User user = getCurrentUser();


        if (!passwordEncoder.matches(
                request.getOldPassword(),
                user.getPassword()
        )) {

            throw new RuntimeException(
                    "Current password is incorrect"
            );
        }


        user.setPassword(
                passwordEncoder.encode(
                        request.getNewPassword()
                )
        );


        userRepository.save(user);
    }

}