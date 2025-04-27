package com.example.academy.modules.topic.controller;

import com.example.academy.core.domain.request.attendance.AttendanceRequest;
import com.example.academy.core.domain.response.attendance.AttendanceResponse;
import com.example.academy.modules.topic.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/attendance")
@RequiredArgsConstructor
public class AttendanceController {


    private final AttendanceService attendanceService;

    @GetMapping
    public ResponseEntity<?> getAllAttendances() {
        List<AttendanceResponse> attendances = attendanceService.getAllAttendances();
        return ResponseEntity.ok(attendances);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceResponse> getAttendanceById(@PathVariable Long id) {
        AttendanceResponse attendance = attendanceService.getAttendanceById(id);
        return ResponseEntity.ok(attendance);
    }

    @PostMapping
    public ResponseEntity<AttendanceResponse> createAttendance(@RequestBody AttendanceRequest request) {
        AttendanceResponse created = attendanceService.createAttendance(request);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttendanceResponse> updateAttendance(@PathVariable Long id,
                                                               @RequestBody AttendanceRequest request) {
        AttendanceResponse updated = attendanceService.updateAttendance(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
        return ResponseEntity.noContent().build();
    }

}
