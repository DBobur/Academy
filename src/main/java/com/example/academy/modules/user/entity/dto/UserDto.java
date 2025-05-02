package com.example.academy.modules.user.entity.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
public class UserDto {
    private Long id;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private String fullName;
    private String username;
    private String password;
    private String email;
    private String number;
    private String address;
    private LocalDate dateOfBirth;
    private boolean isDeleted;
    private UserContractDto contract;
    private String role;
}


