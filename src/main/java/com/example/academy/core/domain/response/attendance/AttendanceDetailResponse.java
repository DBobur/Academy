package com.example.academy.core.domain.response.attendance;


import lombok.Data;

import java.time.LocalDate;

@Data
public class AttendanceDetailResponse {
    private Long id;
    private Long attendanceId;
    private Long studentId;
    private LocalDate date;
    private boolean isPresent;
}
