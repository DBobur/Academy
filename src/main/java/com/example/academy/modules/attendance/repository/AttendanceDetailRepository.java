package com.example.academy.modules.attendance.repository;

import com.example.academy.modules.attendance.entity.AttendanceDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceDetailRepository extends JpaRepository<AttendanceDetailEntity, Long> {
}

