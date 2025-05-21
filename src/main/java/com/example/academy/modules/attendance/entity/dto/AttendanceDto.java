package com.example.academy.modules.attendance.entity.dto;

import com.example.academy.modules.topic.entity.dto.LessonDto;
import com.example.academy.modules.user.entity.UserEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class AttendanceDto {

    private UserEntity user;
    private LessonDto lesson;
    private List<AttendanceDetailDto> attendanceDetails;
}
