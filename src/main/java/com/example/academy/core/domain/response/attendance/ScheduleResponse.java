package com.example.academy.core.domain.response.attendance;

import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Data
public class ScheduleResponse {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Set<DayOfWeek> daysOfWeek;
    private LocalTime startTime;
    private Long groupId;
    private Long moduleId;
    private Long teacherId;
}
