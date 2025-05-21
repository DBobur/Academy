package com.example.academy.modules.attendance.repository;

import com.example.academy.modules.attendance.entity.AttendanceDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceDetailRepository extends JpaRepository<AttendanceDetailEntity, Long> {
}

