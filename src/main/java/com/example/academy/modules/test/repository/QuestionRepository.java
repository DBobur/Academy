package com.example.academy.modules.test.repository;

import com.example.academy.modules.test.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
}
