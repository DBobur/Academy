package com.example.academy.modules.topic.mapper;

import com.example.academy.modules.topic.entity.SubjectEntity;
import com.example.academy.modules.topic.entity.dto.SubjectDto;

import java.util.stream.Collectors;

public class SubjectMapper {
    public static SubjectDto toDtoWith(SubjectEntity subject){
        SubjectDto subjectDto=toDto(subject);
        subjectDto.setModules(
                subject.getModules()
                        .stream()
                        .map(ModuleMapper::toDto)
                        .collect(Collectors.toList())

        );
        return subjectDto;
    }

    public static SubjectDto toDto(SubjectEntity subject){
        return SubjectDto.builder()
                .id(subject.getId())
                .name(subject.getName())
                .description(subject.getDescription())
                .build();
    }

    public  static SubjectEntity toEntity(SubjectDto subjectDto){
        return SubjectEntity.builder()
                .name(subjectDto.getName())
                .description(subjectDto.getDescription())
                .build();
    }
    public static SubjectEntity toEntityWith(SubjectDto subjectDto){
        SubjectEntity subjectEntity=toEntity(subjectDto);
        subjectEntity.setModules(
                subjectDto.getModules()
                        .stream()
                        .map(ModuleMapper::toEntity)
                .collect(Collectors.toList())
        );
        return subjectEntity;
    }

}
