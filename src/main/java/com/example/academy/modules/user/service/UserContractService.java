package com.example.academy.modules.user.service;

import com.example.academy.core.domain.request.user.UserContractRequest;
import com.example.academy.core.domain.response.user.UserContractResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.example.academy.modules.user.entity.UserContract;
import com.example.academy.modules.user.repository.UserContractRepository;
import com.example.academy.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class UserContractService {

    private final UserContractRepository contractRepo;
    private final UserRepository userRepo;

    @Transactional // 👈 aynan shu kerak
    public List<UserContractResponse> getAllByUserId(Long userId) {
        var contracts = contractRepo.findByUserId(userId);
        System.out.println("Contracts found: " + contracts.size());
        contracts.forEach(c -> System.out.println("Contract ID: " + c.getId()));
        return contracts.stream()
                .map(this::toDto)
                .collect(toList());
    }

    public UserContractResponse getById(Long id) {
        var contract = contractRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found"));

        return toDto(contract);
    }


    public UserContractResponse create(UserContractRequest request) {
        var user = userRepo.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        var contract = new UserContract();
        contract.setUser(user);
        contract.setContractTy(request.getContractTy());
        contract.setStartDate(request.getStartDate());
        contract.setEndDate(request.getEndDate());
        contract.setStatus(request.getStatus());
        contract.setContractDetails(request.getContractDetails());

        contractRepo.save(contract);
        return toDto(contract);
    }

    public UserContractResponse update(Long id, UserContractRequest request) {
        var contract = contractRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found"));

        contract.setContractTy(request.getContractTy());
        contract.setStartDate(request.getStartDate());
        contract.setEndDate(request.getEndDate());
        contract.setStatus(request.getStatus());
        contract.setContractDetails(request.getContractDetails());

        contractRepo.save(contract);
        return toDto(contract);
    }

    public void delete(Long id) {
        contractRepo.deleteById(id);
    }

    private UserContractResponse toDto(UserContract c) {
        return UserContractResponse.builder()
                .id(c.getId())
                .userId(c.getUser().getId())
                .userFullName(c.getUser().getFullName())
                .contractTy(c.getContractTy())
                .startDate(c.getStartDate())
                .endDate(c.getEndDate())
                .status(c.getStatus())
                .contractDetails(c.getContractDetails())
                .build();
    }
}
