package com.example.academy.modules.attendance.entity;

import com.example.academy.core.common.BaseEntity;
import com.example.academy.modules.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(
        name = "attendance_details"
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AttendanceDetailEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private AttendanceEntity attendance;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity student;

    private LocalDate date;

    private boolean isPresent;
}
