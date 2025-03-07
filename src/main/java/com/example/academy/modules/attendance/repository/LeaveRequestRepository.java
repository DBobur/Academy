package com.example.academy.modules.attendance.repository;

import com.example.academy.modules.attendance.entity.LeaveRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequestEntity, Long> {
}
