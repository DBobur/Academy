package com.example.academy.modules.topic.entity.dto;

import com.example.academy.modules.topic.entity.LessonEntity;
import com.example.academy.modules.topic.entity.SubjectEntity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

public class ModuleDto {
    private String title;
    private String description;
    private SubjectDto topic;
}
