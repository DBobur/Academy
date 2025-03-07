package com.example.academy.modules.attendance.entity;

import com.example.academy.core.common.BaseEntity;
import com.example.academy.modules.user.entity.UserEntity;
import com.example.academy.modules.user.entity.UserGroup;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;


import java.time.LocalDateTime;

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
    private String eventName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "group_id")  // Foreign key
    private UserGroup group;
}
