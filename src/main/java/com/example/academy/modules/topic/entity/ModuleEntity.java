package com.example.academy.modules.topic.entity;

import com.example.academy.core.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(
        name = "modules"
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ModuleEntity extends BaseEntity {
    private String title;
    private String description;
    @ManyToOne(fetch = FetchType.EAGER)
    private SubjectEntity topic;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<LessonEntity> lessons;

}
