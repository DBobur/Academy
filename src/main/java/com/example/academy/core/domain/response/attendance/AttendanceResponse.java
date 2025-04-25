package com.example.academy.core.domain.response.attendance;

import lombok.Data;

@Data
public class AttendanceResponse {
    private Long id;
    private Long teacherId;
    private Long scheduleId;
}

