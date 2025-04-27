package com.example.academy.modules.topic.controller;

import com.example.academy.core.domain.request.attendance.ScheduleRequest;
import com.example.academy.core.domain.response.attendance.ScheduleResponse;
import com.example.academy.modules.topic.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/schedule")
@RequiredArgsConstructor
public class ScheduleController {


    private final ScheduleService scheduleService;

    @GetMapping
    public ResponseEntity<?> getAllSchedules() {
        List<ScheduleResponse> schedules = scheduleService.getAllSchedules();
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponse> getScheduleById(@PathVariable Long id) {
        ScheduleResponse schedule = scheduleService.getScheduleById(id);
        return ResponseEntity.ok(schedule);
    }

    @PostMapping
    public ResponseEntity<ScheduleResponse> createSchedule(@RequestBody ScheduleRequest request) {
        ScheduleResponse created = scheduleService.createSchedule(request);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponse> updateSchedule(@PathVariable Long id,
                                                           @RequestBody ScheduleRequest request) {
        ScheduleResponse updated = scheduleService.updateSchedule(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }

}
