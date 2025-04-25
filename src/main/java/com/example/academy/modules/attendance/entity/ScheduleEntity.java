package com.example.academy.modules.attendance.entity;

import com.example.academy.core.common.BaseEntity;
import com.example.academy.modules.topic.entity.ModuleEntity;
import com.example.academy.modules.user.entity.UserEntity;
import com.example.academy.modules.user.entity.UserGroup;
import jakarta.persistence.*;
import lombok.*;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "schedules")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ScheduleEntity extends BaseEntity {

    private LocalDate startDate;
    private LocalDate endDate;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "schedule_days", joinColumns = @JoinColumn(name = "schedule_id"))
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> daysOfWeek;

    private LocalTime startTime;

    private Long groupId;

    private Long moduleId;

    private Long teacherId;
}


