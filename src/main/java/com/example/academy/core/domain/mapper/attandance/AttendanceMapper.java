package com.example.academy.core.domain.mapper.attandance;

import com.example.academy.core.domain.request.topic.module.attandance.AttendanceRequest;
import com.example.academy.core.domain.response.attandance.AttendanceResponse;
import com.example.academy.core.domain.response.attandance.AttendanceWithDetailResponse;
import com.example.academy.modules.attendance.entity.AttendanceDetailEntity;
import com.example.academy.modules.attendance.entity.AttendanceEntity;
import com.example.academy.modules.topic.entity.LessonEntity;
import com.example.academy.modules.user.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class AttendanceMapper {

    public static AttendanceEntity requestToEntity(
            AttendanceRequest request,
            UserEntity user,
            LessonEntity lesson,
            List<AttendanceDetailEntity> details
    ) {
        return AttendanceEntity.builder()
                .user(user)
                .lesson(lesson)
                .attendanceDetails(details)
                .build();
    }

    public static AttendanceResponse entityToResponse(AttendanceEntity entity) {
        List<AttendanceWithDetailResponse> detailResponses = entity.getAttendanceDetails().stream()
                .map(AttendanceWithDetailMapper::entityToResponse)
                .collect(Collectors.toList());

        return AttendanceResponse.builder()
                .id(entity.getId())
                .userId(entity.getUser().getId())
                .userFullName(entity.getUser().getFullName())
                .lessonId(entity.getLesson().getId())
                .lessonTitle(entity.getLesson().getTitle())
                .attendanceDetails(detailResponses)
                .createdDate(entity.getCreatedTime())
                .updatedDate(entity.getUpdatedTime())
                .build();
    }


}
