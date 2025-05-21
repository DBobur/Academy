package com.example.academy.modules.attendance.entity.dto;

import com.example.academy.modules.attendance.entity.AttendanceEntity;
import com.example.academy.modules.user.entity.UserEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class AttendanceDetailDto {

    private AttendanceEntity attendance;
    private UserEntity student;
    private LocalDate date;
    private boolean isPresent;
}
