package com.example.academy.modules.attendance.controller;

import com.example.academy.core.domain.request.attendance.AttendanceDetailRequest;
import com.example.academy.core.domain.response.attendance.AttendanceDetailResponse;
import com.example.academy.modules.attendance.service.AttendanceDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance-details")
@RequiredArgsConstructor
public class AttendanceDetailController {

    private final AttendanceDetailService detailService;

    @PostMapping
    public AttendanceDetailResponse create(@RequestBody AttendanceDetailRequest request) {
        return detailService.create(request);
    }

    @GetMapping
    public List<AttendanceDetailResponse> getAll() {
        return detailService.getAll();
    }

    @GetMapping("/{id}")
    public AttendanceDetailResponse getById(@PathVariable Long id) {
        return detailService.getById(id);
    }

    @PutMapping("/{id}")
    public AttendanceDetailResponse update(@PathVariable Long id, @RequestBody AttendanceDetailRequest request) {
        return detailService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        detailService.delete(id);
    }
}

