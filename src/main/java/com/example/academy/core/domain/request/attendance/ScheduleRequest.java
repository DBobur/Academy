package com.example.academy.core.domain.request.attendance;

import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Data
public class ScheduleRequest {
    private LocalDate startDate;
    private LocalDate endDate;
    private Set<DayOfWeek> daysOfWeek;
    private LocalTime startTime;
    private Long groupId;
    private Long moduleId;
    private Long teacherId;
}
