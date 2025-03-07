package com.example.academy.modules.test.repository;

import com.example.academy.modules.test.entity.SubmissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionRepository extends JpaRepository<SubmissionEntity, Long> {
}
