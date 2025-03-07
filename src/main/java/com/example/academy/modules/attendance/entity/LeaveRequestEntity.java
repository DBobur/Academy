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
        name = "leave_requests"
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LeaveRequestEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private LocalDate startDate;
    private LocalDate endDate;

    private String reason;
    private String status;
}

