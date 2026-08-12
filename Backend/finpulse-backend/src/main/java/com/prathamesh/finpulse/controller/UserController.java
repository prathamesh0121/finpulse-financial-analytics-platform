package com.prathamesh.finpulse.controller;

import com.prathamesh.finpulse.dto.request.LoginRequest;
import com.prathamesh.finpulse.dto.request.RegisterRequest;
import com.prathamesh.finpulse.dto.request.UpdateUserRequest;
import com.prathamesh.finpulse.dto.response.LoginResponse;
import com.prathamesh.finpulse.dto.response.RegisterResponse;
import com.prathamesh.finpulse.dto.response.UserResponse;
import com.prathamesh.finpulse.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
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

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }

    @GetMapping("/users/{id}")
    public UserResponse getUserById(@PathVariable Long id)
    {
        return userService.getUserById(id);
    }

    @GetMapping("/users")
    public Page<UserResponse> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "5") int size,
                                          @RequestParam(required = false) String fullName)
    {
        return userService.getAllUsers(page, size, fullName);
    }

    @PutMapping("/users/{id}")
    public UserResponse updateUser(@PathVariable Long id,
                                   @Valid @RequestBody UpdateUserRequest request)
    {
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id)
    {
        userService.deleteUser(id);
        return "User Deleted Successfully";
    }
}