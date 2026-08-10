package com.prathamesh.finpulse.controller;

import com.prathamesh.finpulse.dto.request.RegisterRequest;
import com.prathamesh.finpulse.dto.response.RegisterResponse;
import com.prathamesh.finpulse.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse register(@Valid @RequestBody RegisterRequest request) {
        System.out.println("Register API Called");
        return userService.register(request);
    }
}