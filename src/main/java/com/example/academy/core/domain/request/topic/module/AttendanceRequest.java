package com.example.academy.core.domain.request.topic.module;

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
    private Long userId;  // The user ID (student).
    private Long lessonId;  // The lesson ID that this attendance relates to.
    private List<AttendanceDetailRequest> attendanceDetails;
}
