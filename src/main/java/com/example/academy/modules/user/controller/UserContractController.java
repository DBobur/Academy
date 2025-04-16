package com.example.academy.modules.user.controller;

import com.example.academy.core.domain.request.user.UserContractRequest;
import com.example.academy.core.domain.response.user.UserContractResponse;
import com.example.academy.modules.user.service.UserContractService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contracts")
@RequiredArgsConstructor
public class UserContractController {

    private final UserContractService contractService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserContractResponse>> getUserContracts(@PathVariable Long userId) {
        return ResponseEntity.ok(contractService.getAllByUserId(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserContractResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(contractService.getById(id));
    }


    @PostMapping
    public ResponseEntity<UserContractResponse> create(@Valid @RequestBody UserContractRequest request) {
        return ResponseEntity.ok(contractService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserContractResponse> update(@PathVariable Long id,
                                                       @Valid @RequestBody UserContractRequest request) {
        return ResponseEntity.ok(contractService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        contractService.delete(id);
        return ResponseEntity.ok("Contract deleted");
    }
}

