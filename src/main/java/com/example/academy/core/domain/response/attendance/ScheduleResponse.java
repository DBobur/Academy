package com.example.academy.core.domain.response.attendance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
