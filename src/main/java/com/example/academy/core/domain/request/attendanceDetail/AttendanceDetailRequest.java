package com.example.academy.core.domain.request.attendanceDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceDetailRequest {
    private Long studentId;
    private LocalDate date;
    private boolean isPresent;
}
