package com.example.academy.core.domain.mapper.user;

import com.example.academy.modules.user.entity.UserContract;
import com.example.academy.modules.user.entity.dto.UserContractDto;

public class UserContractMapper {
    public static UserContractDto toDto(UserContract userContract) {
        return UserContractDto.builder()
                .id(userContract.getId())
                .contractDetails(userContract.getContractDetails())
                .contractTy(userContract.getContractTy())
                .createdTime(userContract.getCreatedTime())
                .endDate(userContract.getEndDate())
                .updatedTime(userContract.getUpdatedTime())
                .status(userContract.getStatus())
                .build();
    }

    public static UserContractDto toDtoWith(UserContract userContract) {
        UserContractDto dto = toDto(userContract);
        dto.setUser(
                UserMapper.toDto(userContract.getUser())
        );
        return dto;
    }

    public static UserContract toEntity(UserContractDto contract) {
        return UserContract.builder()
                .contractDetails(contract.getContractDetails())
                .contractTy(contract.getContractTy())
                .status(contract.getStatus())
                .startDate(contract.getStartDate())
                .endDate(contract.getEndDate())
                .build();
    }
}
