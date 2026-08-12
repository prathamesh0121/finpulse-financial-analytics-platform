package com.prathamesh.finpulse.service;

import com.prathamesh.finpulse.dto.request.LoginRequest;
import com.prathamesh.finpulse.dto.request.RegisterRequest;
import com.prathamesh.finpulse.dto.request.UpdateUserRequest;
import com.prathamesh.finpulse.dto.response.LoginResponse;
import com.prathamesh.finpulse.dto.response.RegisterResponse;
import com.prathamesh.finpulse.dto.response.UserResponse;
import org.springframework.data.domain.Page;

public interface UserService {

    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

    UserResponse getUserById(Long id);

    Page<UserResponse> getAllUsers(
            int page,
            int size,
            String fullName
    );

    UserResponse updateUser(
            Long id,
            UpdateUserRequest request
    );

    void deleteUser(Long id);
}