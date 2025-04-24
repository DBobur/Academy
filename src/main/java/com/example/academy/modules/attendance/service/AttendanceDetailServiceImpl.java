package com.example.academy.modules.attendance.service;


import com.example.academy.core.domain.request.attendance.AttendanceDetailRequest;
import com.example.academy.core.domain.response.attendance.AttendanceDetailResponse;
import com.example.academy.modules.attendance.entity.AttendanceDetailEntity;
import com.example.academy.modules.attendance.repository.AttendanceDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceDetailServiceImpl implements AttendanceDetailService {

    private final AttendanceDetailRepository repository;

    @Override
    public AttendanceDetailResponse create(AttendanceDetailRequest request) {
        AttendanceDetailEntity entity = AttendanceDetailEntity.builder()
                .attendanceId(request.getAttendanceId())
                .studentId(request.getStudentId())
                .date(request.getDate())
                .isPresent(request.isPresent())
                .build();

        return toResponse(repository.save(entity));
    }

    @Override
    public AttendanceDetailResponse update(Long id, AttendanceDetailRequest request) {
        AttendanceDetailEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance detail not found"));

        entity.setAttendanceId(request.getAttendanceId());
        entity.setStudentId(request.getStudentId());
        entity.setDate(request.getDate());
        entity.setPresent(request.isPresent());

        return toResponse(repository.save(entity));
    }

    @Override
    public AttendanceDetailResponse getById(Long id) {
        return repository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Attendance detail not found"));
    }

    @Override
    public List<AttendanceDetailResponse> getAll() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private AttendanceDetailResponse toResponse(AttendanceDetailEntity entity) {
        AttendanceDetailResponse response = new AttendanceDetailResponse();
        response.setId(entity.getId());
        response.setAttendanceId(entity.getAttendanceId());
        response.setStudentId(entity.getStudentId());
        response.setDate(entity.getDate());
        response.setPresent(entity.isPresent());
        return response;
    }
}

