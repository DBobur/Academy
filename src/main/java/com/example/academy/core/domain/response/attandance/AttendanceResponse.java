package com.example.academy.core.domain.response.attandance;

import lombok.Builder;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Builder
public record AttendanceResponse(
        Long id,
        Long userId,
        String userFullName,
        Long lessonId,
        String lessonTitle,
        List<AttendanceWithDetailResponse> attendanceDetails,
        LocalDateTime createdDate,
        LocalDateTime updatedDate
) {}
