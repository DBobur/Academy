package com.example.academy.modules.user.entity.dto;

import com.example.academy.modules.user.enums.ContractStatus;
import com.example.academy.modules.user.enums.ContractType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
public class UserContractDto{
    private Long id;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private UserDto user;
    private ContractType contractTy;
    private LocalDate startDate;
    private LocalDate endDate;
    private ContractStatus status;
    private String contractDetails;
}

