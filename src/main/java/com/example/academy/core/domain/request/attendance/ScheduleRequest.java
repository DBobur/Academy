package com.example.academy.core.domain.request.attendance;

import lombok.Builder;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Data
@Builder
public class ScheduleRequest {
    private LocalDate startDate;
    private LocalDate endDate;
    private Set<DayOfWeek> daysOfWeek;
    private LocalTime startTime;
    private Long groupId;
    private Long moduleId;
    private Long teacherId;
}
