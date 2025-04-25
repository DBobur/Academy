package com.example.academy.modules.attendance.service;



import com.example.academy.core.domain.request.attendance.AttendanceRequest;
import com.example.academy.core.domain.response.attendance.AttendanceResponse;

import java.util.List;

public interface AttendanceService {
    AttendanceResponse create(AttendanceRequest request);
    AttendanceResponse update(Long id, AttendanceRequest request);
    AttendanceResponse getById(Long id);
    List<AttendanceResponse> getAll();
    void delete(Long id);
}

