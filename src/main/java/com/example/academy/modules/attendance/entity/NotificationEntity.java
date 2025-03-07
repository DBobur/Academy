package com.example.academy.modules.attendance.entity;

import com.example.academy.core.common.BaseEntity;
import com.example.academy.modules.user.entity.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "notifications"
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class NotificationEntity extends BaseEntity {
    private String message;
    private boolean isRead;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private LocalDateTime timestamp;

    // Constructors, Getters, Setters
}

