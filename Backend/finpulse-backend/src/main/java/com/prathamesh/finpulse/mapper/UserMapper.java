package com.prathamesh.finpulse.mapper;

import com.prathamesh.finpulse.dto.request.RegisterRequest;
import com.prathamesh.finpulse.dto.response.LoginResponse;
import com.prathamesh.finpulse.dto.response.RegisterResponse;
import com.prathamesh.finpulse.dto.response.UserResponse;
import com.prathamesh.finpulse.entity.Role;
import com.prathamesh.finpulse.entity.User;

public class UserMapper {

    public static User toEntity(RegisterRequest request) {

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(Role.USER);

        return user;
    }

    public static RegisterResponse response(User user) {

        return RegisterResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .message("User registered successfully")
                .build();
    }

    public static LoginResponse toLoginResponse(User user) {

        return LoginResponse.builder()
                .message("Login Successful")
                .build();
    }

    public static UserResponse toUserResponse(User user) {

        return UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }
}