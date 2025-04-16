package com.example.academy.modules.user.repository;

import com.example.academy.modules.user.entity.UserContract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserContractRepository extends JpaRepository<UserContract, Long> {
    List<UserContract> findByUserId(Long userId);
}
