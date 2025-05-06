package com.example.academy.modules.topic.mapper;

import com.example.academy.modules.topic.entity.ModuleEntity;
import com.example.academy.modules.topic.entity.dto.ModuleDto;
import com.example.academy.modules.user.entity.dto.UserDto;

import java.util.stream.Collectors;

public class ModuleMapper {

    public static ModuleDto toDtoWith(ModuleEntity module) {
        ModuleDto moduleDto=toDto(module);
        moduleDto.setLessons(
                module.getLessons()
                        .stream()
                        .map(LessonMapper::toDto)
                        .collect(Collectors.toList()
        ));
        return moduleDto;

    }

    public static ModuleDto toDto(ModuleEntity module) {
        return ModuleDto.builder()
                .id(module.getId())
                .title(module.getTitle())
                .description(module.getDescription())
                .topic(SubjectMapper.toDto(module.getTopic()))
                .build();
    }

    public static ModuleEntity toEntity(ModuleDto moduleDto) {
        return ModuleEntity.builder()
                .title(moduleDto.getTitle())
                .description(moduleDto.getDescription())
                .topic(SubjectMapper.toEntity(moduleDto.getTopic()))
                .build();
    }

    public static ModuleEntity toEntityWith(ModuleDto moduleDto) {
        ModuleEntity moduleEntity=toEntity(moduleDto);
        moduleEntity.setLessons(
                moduleDto.getLessons()
                        .stream()
                        .map(LessonMapper::toEntity)
                        .collect(Collectors.toList())
        );
        return moduleEntity;
    }
}
