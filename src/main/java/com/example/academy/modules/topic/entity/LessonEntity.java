package com.example.academy.modules.topic.entity;

import com.example.academy.core.common.BaseEntity;
import com.example.academy.modules.attendance.entity.AttendanceEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Entity
@Table(
        name = "lessons"
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LessonEntity extends BaseEntity {
    private String title;
    private String content;
    @ManyToOne
    private ModuleEntity module;
    @OneToMany
    private List<ResourceEntity> resources;
    @OneToMany
    private List<AttendanceEntity> attendances;
}
