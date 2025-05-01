package com.example.academy.modules.topic.entity;

import com.example.academy.core.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(
        name = "resources"
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ResourceEntity extends BaseEntity {
    @ManyToOne
    private LessonEntity lesson;
    private String content;
}
