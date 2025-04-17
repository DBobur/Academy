package com.example.academy.modules.topic.repository;

import com.example.academy.modules.topic.entity.ModuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleEntityRepository extends JpaRepository<ModuleEntity, Long> {
}