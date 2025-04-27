package com.example.academy.modules.topic.controller;

import com.example.academy.core.domain.request.attendance.AttendanceDetailRequest;
import com.example.academy.core.domain.response.attendance.AttendanceDetailResponse;
import com.example.academy.modules.topic.service.AttendanceDetailService;
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
        List<AttendanceDetailResponse> details = attendanceDetailService.getAllAttendanceDetails();
        return ResponseEntity.ok(details);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceDetailResponse> getAttendanceDetailById(@PathVariable Long id) {
        AttendanceDetailResponse detail = attendanceDetailService.getAttendanceDetailById(id);
        return ResponseEntity.ok(detail);
    }

    @PostMapping
    public ResponseEntity<AttendanceDetailResponse> createAttendanceDetail(
            @RequestBody AttendanceDetailRequest request) {
        AttendanceDetailResponse created = attendanceDetailService.createAttendanceDetail(request);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttendanceDetailResponse> updateAttendanceDetail(
            @PathVariable Long id,
            @RequestBody AttendanceDetailRequest request) {
        AttendanceDetailResponse updated = attendanceDetailService.updateAttendanceDetail(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAttendanceDetail(@PathVariable Long id) {
        attendanceDetailService.deleteAttendanceDetail(id);
        return ResponseEntity.noContent().build();
    }

}
