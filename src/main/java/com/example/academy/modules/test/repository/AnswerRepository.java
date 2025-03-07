package com.example.academy.modules.test.repository;

import com.example.academy.modules.test.entity.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {
}
