package com.example.academy.modules.user.entity.mapper;

import com.example.academy.core.domain.mapper.user.UserContractMapper;
import com.example.academy.modules.user.entity.UserEntity;
import com.example.academy.modules.user.entity.dto.UserDto;
import com.example.academy.modules.user.enums.UserRole;

public class UserMapper {
    public static UserDto toDtoWith(UserEntity user) {
        UserDto dto = toDto(user);
        dto.setContract(
                user.getContract() != null ?
                UserContractMapper.toDto(user.getContract()):null
        );
        return dto;
    }

    public static UserDto toDto(UserEntity user) {
        return UserDto.builder()
                .id(user.getId())
                .createdTime(user.getCreatedTime())
                .updatedTime(user.getUpdatedTime())
                .number(user.getNumber())
                .username(user.getUsername())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(String.valueOf(user.getRole()))
                .dateOfBirth(user.getDateOfBirth())
                .address(user.getAddress())
                .build();
    }

    public static UserEntity toEntity(UserDto userDto) {
        return UserEntity.builder()
                .number(userDto.getNumber())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .fullName(userDto.getFullName())
                .email(userDto.getEmail())
                .isDeleted(userDto.isDeleted())
                .role(UserRole.valueOf(userDto.getRole()))
                .dateOfBirth(userDto.getDateOfBirth())
                .address(userDto.getAddress())
                .build();
    }
    public static UserEntity toEntityWith(UserDto userDto) {
        UserEntity entity = toEntity(userDto);
        entity.setContract(
                userDto.getContract() != null ?
                UserContractMapper.toEntity(userDto.getContract()):null
        );
        return entity;
    }
}
