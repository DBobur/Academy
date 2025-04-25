package com.example.academy.modules.attendance.service;

import com.example.academy.core.domain.request.attendance.AttendanceRequest;
import com.example.academy.core.domain.response.attendance.AttendanceResponse;
import com.example.academy.modules.attendance.entity.AttendanceEntity;
import com.example.academy.modules.attendance.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;

    @Override
    public AttendanceResponse create(AttendanceRequest request) {
        AttendanceEntity attendance = AttendanceEntity.builder()
                .teacherId(request.getTeacherId())
                .scheduleId(request.getScheduleId())
                .build();

        return toResponse(attendanceRepository.save(attendance));
    }

    @Override
    public AttendanceResponse update(Long id, AttendanceRequest request) {
        AttendanceEntity attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        attendance.setTeacherId(request.getTeacherId());
        attendance.setScheduleId(request.getScheduleId());

        return toResponse(attendanceRepository.save(attendance));
    }

    @Override
    public AttendanceResponse getById(Long id) {
        return attendanceRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));
    }

    @Override
    public List<AttendanceResponse> getAll() {
        return attendanceRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        attendanceRepository.deleteById(id);
    }

    private AttendanceResponse toResponse(AttendanceEntity entity) {
        AttendanceResponse response = new AttendanceResponse();
        response.setId(entity.getId());
        response.setTeacherId(entity.getTeacherId());
        response.setScheduleId(entity.getScheduleId());
        return response;
    }
}

