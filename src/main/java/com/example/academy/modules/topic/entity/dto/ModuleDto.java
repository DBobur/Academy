package com.example.academy.modules.topic.entity.dto;

import com.example.academy.modules.topic.entity.LessonEntity;
import com.example.academy.modules.topic.entity.SubjectEntity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data
public class ModuleDto {
    private Long id;
    private String title;
    private String description;
    private SubjectDto topic;
    private List<LessonDto> lessons;

}
