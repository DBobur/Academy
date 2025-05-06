package com.example.academy.modules.topic.entity;

import com.example.academy.core.common.BaseEntity;
import jakarta.persistence.*;
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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ModuleEntity> modules;



}
