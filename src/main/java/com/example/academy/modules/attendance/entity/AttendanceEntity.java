package com.example.academy.modules.attendance.entity;

import com.example.academy.core.common.BaseEntity;
import com.example.academy.modules.topic.entity.LessonEntity;
import com.example.academy.modules.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(
        name = "attendances"
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AttendanceEntity extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    private LessonEntity lesson;

    @OneToMany(fetch = FetchType.LAZY)
    private List<AttendanceDetailEntity> attendanceDetails;
}

