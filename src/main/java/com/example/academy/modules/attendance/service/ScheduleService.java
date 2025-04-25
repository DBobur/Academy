package com.example.academy.modules.attendance.service;


import com.example.academy.core.domain.request.attendance.ScheduleRequest;
import com.example.academy.core.domain.response.attendance.ScheduleResponse;

import java.util.List;

public interface ScheduleService {
    ScheduleResponse create(ScheduleRequest request);
    ScheduleResponse update(Long id, ScheduleRequest request);
    ScheduleResponse getById(Long id);
    List<ScheduleResponse> getAll();
    void delete(Long id);
}

