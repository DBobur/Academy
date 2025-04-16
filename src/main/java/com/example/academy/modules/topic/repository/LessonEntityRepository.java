package com.example.academy.modules.topic.repository;

import com.example.academy.modules.topic.entity.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonEntityRepository extends JpaRepository<LessonEntity, Long> {
}