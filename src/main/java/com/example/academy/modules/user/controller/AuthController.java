package com.example.academy.modules.user.controller;

import com.example.academy.core.domain.request.user.LoginRequest;
import com.example.academy.core.domain.request.user.UserRequest;
import com.example.academy.core.domain.response.user.UserResponse;
import com.example.academy.modules.user.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        String token = authService.login(request);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRequest request) {

            UserResponse userResponse = authService.save(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }
}
