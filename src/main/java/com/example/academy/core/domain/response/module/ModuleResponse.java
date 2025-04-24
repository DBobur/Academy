package com.example.academy.core.domain.response.module;

import com.example.academy.modules.topic.entity.LessonEntity;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ModuleResponse(Long id,
                             LocalDateTime createdDate,
                             LocalDateTime updatedDate,
                             String title,
                             String description,
                             Long topicId,
                             String topicName,
                             List<LessonEntity> lessons) {
}
