package com.example.academy.modules.attendance.controller;

import com.example.academy.core.domain.request.attendance.AttendanceRequest;
import com.example.academy.core.domain.response.attendance.AttendanceResponse;
import com.example.academy.modules.attendance.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendances")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping
    public AttendanceResponse create(@RequestBody AttendanceRequest request) {
        return attendanceService.create(request);
    }

    @GetMapping
    public List<AttendanceResponse> getAll() {
        return attendanceService.getAll();
    }

    @GetMapping("/{id}")
    public AttendanceResponse getById(@PathVariable Long id) {
        return attendanceService.getById(id);
    }

    @PutMapping("/{id}")
    public AttendanceResponse update(@PathVariable Long id, @RequestBody AttendanceRequest request) {
        return attendanceService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        attendanceService.delete(id);
    }
}
