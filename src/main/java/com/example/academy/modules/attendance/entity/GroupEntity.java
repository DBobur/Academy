package com.example.academy.modules.attendance.entity;

import com.example.academy.core.common.BaseEntity;
import com.example.academy.modules.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Entity
@Table(
        name = "groups"
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class GroupEntity extends BaseEntity {

    // group name
    private String groupName;

    // subject name, unique
    @Column(unique = true)
    private String subjectName;

    // subject user, or created user
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;

    private LocalTime startTime;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<UserEntity> teachers;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<UserEntity> users;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ScheduleEntity> schedules;
}
