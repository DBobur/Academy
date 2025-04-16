package com.example.academy.core.domain.response.user;

import com.example.academy.modules.user.enums.ContractStatus;
import com.example.academy.modules.user.enums.ContractType;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UserContractResponse(
        Long id,
        Long userId,
        String userFullName,
        ContractType contractTy,
        LocalDate startDate,
        LocalDate endDate,
        ContractStatus status,
        String contractDetails
) {}
