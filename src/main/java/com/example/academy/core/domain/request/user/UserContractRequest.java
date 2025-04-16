package com.example.academy.core.domain.request.user;

import com.example.academy.modules.user.enums.ContractStatus;
import com.example.academy.modules.user.enums.ContractType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserContractRequest {

    @NotNull
    private Long userId;

    @NotNull
    private ContractType contractTy;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    private ContractStatus status;

    private String contractDetails;
}
