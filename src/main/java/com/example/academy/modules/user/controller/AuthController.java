package com.example.academy.modules.user.controller;

import com.example.academy.core.domain.request.user.LoginRequest;
import com.example.academy.core.domain.request.user.UserRequest;
import com.example.academy.core.domain.response.user.UserResponse;
import com.example.academy.modules.user.service.Auth2Service;
import com.example.academy.modules.user.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    //private final Auth2Service auth2Service;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        String token = authService.login(request);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    //@PreAuthorize("hasAnyRole('SUPER','ADMIN','MANEGER')")
    public ResponseEntity<?> register(@Valid @RequestBody UserRequest request) {

            UserResponse userResponse = authService.save(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    /*@GetMapping("/oauth2/link")
    public ResponseEntity<Map<String, String>> getOAuth2Link() {
        String authorizationUrl = auth2Service.generateGoogleOAuth2Link();
        return ResponseEntity.ok(Map.of("url", authorizationUrl));
    }

    @GetMapping("/oauth2/code")
    public ResponseEntity<Map<String, String>> handleOAuth2Code(@RequestParam String code) {
        String jwtToken = auth2Service.handleOAuth2Code(code);
        System.out.println(jwtToken);
        return ResponseEntity.ok(Map.of("token", jwtToken));
    }*/
}
