package com.example.academy.core.domain.response.user;

import com.example.academy.modules.user.enums.UserRole;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record UserResponse(Long id,
                           LocalDateTime createdDate,
                           LocalDateTime updatedDate,
                           String fullName,
                           String username,
                           String email,
                           String number,
                           String address,
                           LocalDate dateOfBirth,
                           UserRole role) {}

