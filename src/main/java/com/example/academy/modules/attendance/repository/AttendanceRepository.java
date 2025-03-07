package com.example.academy.modules.attendance.repository;

import com.example.academy.modules.attendance.entity.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<AttendanceEntity,Long> {
}
