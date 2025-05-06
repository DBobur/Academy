package com.example.academy.modules.topic.mapper;

import com.example.academy.modules.topic.entity.LessonEntity;
import com.example.academy.modules.topic.entity.dto.LessonDto;

import java.util.stream.Collectors;

public class LessonMapper {

    public static LessonDto toDtoWith(LessonEntity lesson) {
        LessonDto lessonDto=toDto(lesson);
        lessonDto.setResources(
                lesson.getResources()
                        .stream()
                        .map(ResourceMapper::toDto)
                        .collect(Collectors.toList())

        );
        return lessonDto;

    }

    public static LessonDto toDto(LessonEntity lesson){
        return LessonDto.builder()
                .id(lesson.getId())
                .title(lesson.getTitle())
                .content(lesson.getContent())
                .module(ModuleMapper.toDto(lesson.getModule()))
                .build();
    }

    public static LessonEntity toEntity(LessonDto lessonDto) {
        return LessonEntity.builder()
                .title(lessonDto.getTitle())
                .content(lessonDto.getContent())
                .module(ModuleMapper.toEntity(lessonDto.getModule()))
                .build();
    }
    public LessonEntity toEntityWith(LessonDto lessonDto) {
        LessonEntity lessonEntity = toEntity(lessonDto);
        lessonEntity.setResources(
                lessonDto.getResources()
                        .stream()
                        .map(ResourceMapper::toEntity)
                         .collect(Collectors.toList())
        );
        return lessonEntity;
    }
}
