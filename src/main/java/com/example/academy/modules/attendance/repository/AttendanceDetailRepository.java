package com.example.academy.modules.attendance.repository;

import com.example.academy.modules.attendance.entity.AttendanceDetailEntity;
import com.example.academy.modules.user.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceDetailRepository extends JpaRepository<AttendanceDetailEntity,Long> {

    List<AttendanceDetailEntity>  findALlByUserEntity(UserEntity userEntity);
}
