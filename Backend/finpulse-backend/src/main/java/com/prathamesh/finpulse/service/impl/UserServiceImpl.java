package com.prathamesh.finpulse.service.impl;

import com.prathamesh.finpulse.dto.request.RegisterRequest;
import com.prathamesh.finpulse.dto.response.RegisterResponse;
import com.prathamesh.finpulse.entity.User;
import com.prathamesh.finpulse.exception.EmailAlreadyExistsException;
import com.prathamesh.finpulse.mapper.UserMapper;
import com.prathamesh.finpulse.repository.UserRepository;
import com.prathamesh.finpulse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        User user = UserMapper.UserToEntity(request);

        User userSave = userRepository.save(user);

        return  UserMapper.response(userSave);

    }
}
