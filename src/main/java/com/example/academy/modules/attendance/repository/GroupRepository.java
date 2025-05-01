package com.example.academy.modules.attendance.repository;

import com.example.academy.modules.attendance.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
}
