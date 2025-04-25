package com.example.academy.core.domain.request.topic.module.attandance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceRequest {
    private Long userId;
    private Long lessonId;
    private List<AttendanceDetailRequest> attendanceDetails;
}
