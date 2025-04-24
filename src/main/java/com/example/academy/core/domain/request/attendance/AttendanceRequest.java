package com.example.academy.core.domain.request.attendance;

import lombok.Data;

@Data
public class AttendanceRequest {
    private Long teacherId;
    private Long scheduleId;
}
