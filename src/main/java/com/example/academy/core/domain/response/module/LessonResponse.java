package com.example.academy.core.domain.response.module;

import com.example.academy.modules.topic.entity.LessonEntity;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record LessonResponse(
                             Long id,
                             String title,
                             String content,
                             Long moduleId,
                             LocalDateTime createdDate,
                             LocalDateTime updatedDate
) {}
