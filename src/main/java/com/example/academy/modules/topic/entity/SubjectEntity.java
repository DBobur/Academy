package com.example.academy.modules.topic.entity;

import com.example.academy.core.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Entity
@Table(
        name = "topics"
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SubjectEntity extends BaseEntity {
    private String name;
    private String description;
    @OneToMany
    private List<ModuleEntity> modules;
}
