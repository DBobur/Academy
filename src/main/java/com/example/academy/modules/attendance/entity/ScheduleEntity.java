package com.example.academy.modules.attendance.entity;

import com.example.academy.core.common.BaseEntity;
import com.example.academy.modules.topic.entity.ModuleEntity;
import com.example.academy.modules.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(
        name = "schedules"
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ScheduleEntity extends BaseEntity {

    @ElementCollection(targetClass = DayOfWeek.class)
    @CollectionTable(name = "schedule_days", joinColumns = @JoinColumn(name = "schedule_id"))
    @Column(name = "day_of_week")
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> dayOfWeeks;
    private LocalDate startedDate;
    private LocalTime startTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private ModuleEntity module;

    @ManyToOne(fetch = FetchType.LAZY)
    private GroupEntity group;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity teacher;

}

