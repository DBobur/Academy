package com.example.academy.modules.topic.repository;

import com.example.academy.modules.topic.entity.ResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceEntityRepository extends JpaRepository<ResourceEntity, Long> {
}