package com.example.academy.core.domain.mapper.user;

import com.example.academy.core.domain.request.user.UserRequest;
import com.example.academy.core.domain.response.user.UserResponse;
import com.example.academy.modules.user.entity.UserEntity;

import java.time.LocalDate;

public class UserMapper {
    public static UserEntity requestToEntity(UserRequest request) {
        return UserEntity.builder()
                .fullName(request.getFullName())
                .username(request.getUsername())
                .password(request.getPassword())
                .email(request.getEmail())
                .number(request.getNumber())
                .address(request.getAddress())
                .dateOfBirth(LocalDate.parse(request.getDateOfBirth()))
                .build();
    }
    public static UserResponse entityToResponse(UserEntity user) {
        return UserResponse.builder()
                .id(user.getId())
                .createdDate(user.getCreatedTime())
                .updatedDate(user.getUpdatedTime())
                .fullName(user.getFullName())
                .username(user.getUsername())
                .email(user.getEmail())
                .number(user.getNumber())
                .address(user.getAddress())
                .dateOfBirth(user.getDateOfBirth())
                .role(user.getRole())
                .build();
    }
}
