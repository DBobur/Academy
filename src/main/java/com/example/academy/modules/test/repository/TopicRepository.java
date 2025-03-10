package com.example.academy.modules.test.repository;

import com.example.academy.modules.test.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<TestEntity, Long> {
}
