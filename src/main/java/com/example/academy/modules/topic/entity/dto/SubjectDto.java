package com.example.academy.modules.topic.entity.dto;

import com.example.academy.modules.topic.entity.ModuleEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data
public class SubjectDto {
    private Long id;
    private String name;
    private String description;
    private List<ModuleDto> modules;
}
