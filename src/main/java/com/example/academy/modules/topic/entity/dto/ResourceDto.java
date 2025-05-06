package com.example.academy.modules.topic.entity.dto;

import com.example.academy.modules.topic.entity.LessonEntity;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResourceDto {
    private Long id;
    private String content;
    private LessonDto lesson;
}
