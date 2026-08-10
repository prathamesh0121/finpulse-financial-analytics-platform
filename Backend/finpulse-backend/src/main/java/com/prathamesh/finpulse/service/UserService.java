package com.prathamesh.finpulse.service;

import com.prathamesh.finpulse.dto.request.RegisterRequest;
import com.prathamesh.finpulse.dto.response.RegisterResponse;
import com.prathamesh.finpulse.entity.User;

public interface UserService {

    public RegisterResponse register (RegisterRequest request);
}
