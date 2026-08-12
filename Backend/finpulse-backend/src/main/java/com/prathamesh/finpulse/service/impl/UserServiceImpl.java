package com.prathamesh.finpulse.service.impl;

import com.prathamesh.finpulse.dto.request.LoginRequest;
import com.prathamesh.finpulse.dto.request.RegisterRequest;
import com.prathamesh.finpulse.dto.request.UpdateUserRequest;
import com.prathamesh.finpulse.dto.response.LoginResponse;
import com.prathamesh.finpulse.dto.response.RegisterResponse;
import com.prathamesh.finpulse.dto.response.UserResponse;
import com.prathamesh.finpulse.entity.User;
import com.prathamesh.finpulse.exception.EmailAlreadyExistsException;
import com.prathamesh.finpulse.mapper.UserMapper;
import com.prathamesh.finpulse.repository.UserRepository;
import com.prathamesh.finpulse.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        User user = UserMapper.toEntity(request);

        User savedUser = userRepository.save(user);

        return UserMapper.response(savedUser);
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid Email"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        return UserMapper.toLoginResponse(user);
    }

    @Override
    public UserResponse getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User Not Found"));

        return UserMapper.toUserResponse(user);
    }

    @Override
    public Page<UserResponse> getAllUsers(
            int page,
            int size,
            String fullName) {

        Pageable pageable = PageRequest.of(page, size);

        Page<User> users;

        if (fullName != null && !fullName.isBlank()) {

            users = userRepository
                    .findByFullNameContainingIgnoreCase(
                            fullName,
                            pageable
                    );

        } else {

            users = userRepository.findAll(pageable);
        }

        return users.map(UserMapper::toUserResponse);
    }

    @Override
    public UserResponse updateUser(
            Long id,
            UpdateUserRequest request) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User Not Found"));

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());

        User updatedUser = userRepository.save(user);

        return UserMapper.toUserResponse(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User Not Found"));

        userRepository.delete(user);
    }
}