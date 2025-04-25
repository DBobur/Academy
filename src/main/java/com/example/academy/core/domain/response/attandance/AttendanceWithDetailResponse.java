package com.example.academy.core.domain.response.attandance;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record AttendanceWithDetailResponse(
        Long id,
        Long studentId,
        String studentFullName,
        LocalDate date,
        boolean isPresent
) {}
