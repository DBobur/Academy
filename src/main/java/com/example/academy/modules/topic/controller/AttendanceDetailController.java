package com.example.academy.modules.topic.controller;

import com.example.academy.core.domain.request.topic.module.LessonRequest;
import com.example.academy.core.domain.request.topic.module.attandance.AttendanceDetailRequest;
import com.example.academy.core.domain.response.attandance.AttendanceWithDetailResponse;
import com.example.academy.core.domain.response.module.LessonResponse;
import com.example.academy.modules.topic.service.AttendanceDetailService;
import com.example.academy.modules.topic.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/attendance-detail")
@RequiredArgsConstructor
public class AttendanceDetailController {


    private final AttendanceDetailService attendanceDetailService;

    @GetMapping
    public ResponseEntity<?> getAllAttendanceDetails() {
        List<AttendanceWithDetailResponse> details = attendanceDetailService.getAllAttendanceDetails();
        return ResponseEntity.ok(details);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceWithDetailResponse> getAttendanceDetailById(@PathVariable Long id) {
        AttendanceWithDetailResponse detail = attendanceDetailService.getAttendanceDetailById(id);
        return ResponseEntity.ok(detail);
    }

    @PostMapping
    public ResponseEntity<AttendanceWithDetailResponse> createAttendanceDetail(
            @RequestBody AttendanceDetailRequest request) {
        AttendanceWithDetailResponse created = attendanceDetailService.createAttendanceDetail(request);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttendanceWithDetailResponse> updateAttendanceDetail(
            @PathVariable Long id,
            @RequestBody AttendanceDetailRequest request) {
        AttendanceWithDetailResponse updated = attendanceDetailService.updateAttendanceDetail(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAttendanceDetail(@PathVariable Long id) {
        attendanceDetailService.deleteAttendanceDetail(id);
        return ResponseEntity.noContent().build();
    }

}
