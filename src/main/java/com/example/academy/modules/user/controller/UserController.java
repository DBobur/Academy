package com.example.academy.modules.user.controller;

import com.example.academy.modules.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    public ResponseEntity<?> getUserById(Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    /*public ResponseEntity<?> getUserByUsername(String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }*/

    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

}
