package com.example.academy.core.domain.response.attandance;

import lombok.Builder;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Builder
public record ScheduleResponse(
        Long id,
        DayOfWeek dayOfWeek,
        LocalTime startTime,
        LocalTime endTime,
        Long groupId,
        String groupName,
        Long moduleId,
        String moduleTitle,
        Long teacherId,
        String teacherFullName,
        LocalDateTime createdDate,
        LocalDateTime updatedDate
) {}
