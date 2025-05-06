package com.example.academy.modules.topic.entity.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data
public class LessonDto {
    private Long id;
    private String title;
    private String content;
    private ModuleDto module;
    private List<ResourceDto> resources;
}
