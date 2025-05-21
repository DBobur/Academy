package com.example.academy.modules.attendance.repository;

import com.example.academy.modules.attendance.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
    @Query("SELECT g FROM GroupEntity g JOIN FETCH g.users WHERE g.id = :id")
    GroupEntity findByIdWithUsers(@Param("id") Long id);

}
