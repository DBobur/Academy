package com.example.academy.modules.attendance.repository;

import com.example.academy.modules.attendance.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
}
