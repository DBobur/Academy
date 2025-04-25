package com.example.academy.core.domain.mapper.module;

import com.example.academy.core.domain.request.topic.module.LessonRequest;
import com.example.academy.core.domain.response.module.LessonResponse;
import com.example.academy.modules.topic.entity.LessonEntity;
import com.example.academy.modules.topic.entity.ModuleEntity;
import com.example.academy.modules.topic.repository.ModuleEntityRepository;


public class LessonMapper {

    public static LessonEntity requestToLessonEntity(LessonRequest lessonRequest, ModuleEntityRepository moduleRepository) {
        ModuleEntity module = moduleRepository.findById(lessonRequest.getModuleId())
                .orElseThrow(() -> new RuntimeException("Module not found"));

        return LessonEntity.builder()
                .title(lessonRequest.getTitle())
                .content(lessonRequest.getContent())
                .module(module)
                .build();
    }

    public static LessonResponse entityToResponse(LessonEntity lessonEntity) {
        return LessonResponse.builder()
                .id(lessonEntity.getId())
                .title(lessonEntity.getTitle())
                .content(lessonEntity.getContent())
                .moduleId(lessonEntity.getModule().getId())
                .createdDate(lessonEntity.getCreatedTime())
                .updatedDate(lessonEntity.getUpdatedTime())
                .build();
    }


}

