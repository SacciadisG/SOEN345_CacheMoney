package com.soen345.ticketreserve.controller;

import com.soen345.ticketreserve.dto.UserRegistrationRequest;
import com.soen345.ticketreserve.dto.UserResponse;
import com.soen345.ticketreserve.model.User;
import com.soen345.ticketreserve.service.UserService;
import com.soen345.ticketreserve.dto.LoginRequest;
import com.soen345.ticketreserve.dto.UserResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRegistrationRequest request) {
        User user = userService.registerUser(request);

        UserResponse response = new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getRole()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest request) {
        User user = userService.loginUser(request);

        UserResponse response = new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getRole()
        );

        return ResponseEntity.ok(response);
    }

}