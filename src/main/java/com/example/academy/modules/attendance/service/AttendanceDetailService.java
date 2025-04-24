package com.example.academy.modules.attendance.service;


import com.example.academy.core.domain.request.attendance.AttendanceDetailRequest;
import com.example.academy.core.domain.response.attendance.AttendanceDetailResponse;

import java.util.List;

public interface AttendanceDetailService {
    AttendanceDetailResponse create(AttendanceDetailRequest request);
    AttendanceDetailResponse update(Long id, AttendanceDetailRequest request);
    AttendanceDetailResponse getById(Long id);
    List<AttendanceDetailResponse> getAll();
    void delete(Long id);
}

