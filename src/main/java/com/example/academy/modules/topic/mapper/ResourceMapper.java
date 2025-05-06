package com.example.academy.modules.topic.mapper;

import com.example.academy.modules.topic.entity.ResourceEntity;
import com.example.academy.modules.topic.entity.dto.ResourceDto;

public class ResourceMapper {

    public static ResourceDto toDto(ResourceEntity resource) {
        return ResourceDto.builder()
                .id(resource.getId())
                .content(resource.getContent())
                .lesson(LessonMapper.toDto(resource.getLesson()))
                .build();
    }

    public static ResourceEntity toEntity(ResourceDto resourceDto) {
        return ResourceEntity.builder()
                .lesson(LessonMapper.toEntity(resourceDto.getLesson()))
                .content(resourceDto.getContent())
                .build();
    }
}
