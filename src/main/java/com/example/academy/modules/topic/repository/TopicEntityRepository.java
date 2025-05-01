package com.example.academy.modules.topic.repository;

import com.example.academy.modules.topic.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicEntityRepository extends JpaRepository<SubjectEntity, Long> {
}