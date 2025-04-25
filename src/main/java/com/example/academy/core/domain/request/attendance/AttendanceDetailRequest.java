package com.example.academy.core.domain.request.attendance;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AttendanceDetailRequest {
    private Long attendanceId;
    private Long studentId;
    private LocalDate date;
    private boolean isPresent;
}
