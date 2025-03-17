package com.example.academy.modules.user.controller;

import com.example.academy.core.domain.request.user.LoginRequest;
import com.example.academy.core.domain.request.user.UserRequest;
import com.example.academy.core.domain.response.user.UserResponse;
import com.example.academy.modules.user.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        String token = authService.login(request);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    @PreAuthorize("hasAnyRole('SUPER','ADMIN','MANEGER')")
    public ResponseEntity<?> register(@Valid @RequestBody UserRequest request) {

            UserResponse userResponse = authService.save(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @GetMapping("/oauth2/client-info")
    public ResponseEntity<?> getClientInfo() {
        Map<String, String> response = Map.of(
                "clientId", clientId,
                "redirectUri", clientSecret
        );
        return ResponseEntity.ok(response);
    }

}
