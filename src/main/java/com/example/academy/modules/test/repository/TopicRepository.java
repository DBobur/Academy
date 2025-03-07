package com.example.academy.modules.test.repository;

import com.example.academy.modules.test.entity.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<TopicEntity, Long> {
}
