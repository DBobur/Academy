package com.example.academy.modules.attendance.entity;

import com.example.academy.core.common.BaseEntity;
import com.example.academy.modules.user.entity.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

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
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private LocalDate date;
    private boolean isPresent;

    // Constructors, Getters, Setters
}

