package com.example.academy.core.domain.response.attendance;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceDetailResponse {
    private Long id;
    private Long attendanceId;
    private Long studentId;
    private LocalDate date;
    private boolean isPresent;
}
