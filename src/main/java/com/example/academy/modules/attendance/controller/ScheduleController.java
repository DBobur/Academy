package com.example.academy.modules.attendance.controller;


import com.example.academy.core.domain.request.attendance.ScheduleRequest;
import com.example.academy.core.domain.response.attendance.ScheduleResponse;
import com.example.academy.modules.attendance.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ScheduleResponse create(@RequestBody ScheduleRequest request) {
        return scheduleService.create(request);
    }

    @GetMapping
    public List<ScheduleResponse> getAll() {
        return scheduleService.getAll();
    }

    @GetMapping("/{id}")
    public ScheduleResponse getById(@PathVariable Long id) {
        return scheduleService.getById(id);
    }

    @PutMapping("/{id}")
    public ScheduleResponse update(@PathVariable Long id, @RequestBody ScheduleRequest request) {
        return scheduleService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        scheduleService.delete(id);
    }
}

