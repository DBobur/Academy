package com.example.academy.core.domain.response.user;

import com.example.academy.modules.topic.entity.LessonEntity;
import com.example.academy.modules.user.enums.UserRole;
import lombok.Builder;

import java.time.LocalDate;
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
