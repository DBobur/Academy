package com.example.academy.modules.attendance.repository;

import com.example.academy.modules.attendance.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {
}
