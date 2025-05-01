package com.example.academy.modules.topic.entity;

import com.example.academy.core.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
    @ManyToOne
    private SubjectEntity topic;
    @OneToMany
    private List<LessonEntity> lessons;

}
