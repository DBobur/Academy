package com.example.academy.modules.topic.service;
import com.example.academy.core.domain.mapper.attandance.AttendanceWithDetailMapper;
import com.example.academy.core.domain.request.topic.module.attandance.AttendanceDetailRequest;
import com.example.academy.core.domain.response.attandance.AttendanceWithDetailResponse;
import com.example.academy.modules.attendance.entity.AttendanceDetailEntity;
import com.example.academy.modules.attendance.repository.AttendanceDetailEntityRepository;
import com.example.academy.modules.user.entity.UserEntity;
import com.example.academy.modules.user.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AttendanceDetailService {


    private final UserEntityRepository userEntityRepository;
    private final AttendanceDetailEntityRepository attendanceDetailEntityRepository;

    public AttendanceWithDetailResponse createAttendanceDetail(AttendanceDetailRequest request) {
        UserEntity student = userEntityRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        AttendanceDetailEntity entity = AttendanceWithDetailMapper.requestToEntity(request, student);
        AttendanceDetailEntity saved = attendanceDetailEntityRepository.save(entity);
        return AttendanceWithDetailMapper.entityToResponse(saved);
    }

    public List<AttendanceWithDetailResponse> getAllAttendanceDetails() {
        return attendanceDetailEntityRepository.findAll()
                .stream()
                .map(AttendanceWithDetailMapper::entityToResponse)
                .collect(Collectors.toList());
    }

    public AttendanceWithDetailResponse getAttendanceDetailById(Long id) {
        AttendanceDetailEntity entity = attendanceDetailEntityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance detail not found"));
        return AttendanceWithDetailMapper.entityToResponse(entity);
    }

    public AttendanceWithDetailResponse updateAttendanceDetail(Long id, AttendanceDetailRequest request) {
        AttendanceDetailEntity entity = attendanceDetailEntityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance detail not found"));

        UserEntity student = userEntityRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        entity.setDate(request.getDate());
        entity.setPresent(request.isPresent());
        entity.setStudent(student);

        AttendanceDetailEntity updated = attendanceDetailEntityRepository.save(entity);
        return AttendanceWithDetailMapper.entityToResponse(updated);
    }

    public void deleteAttendanceDetail(Long id) {
        if (!attendanceDetailEntityRepository.existsById(id)) {
            throw new RuntimeException("Attendance detail not found");
        }
        attendanceDetailEntityRepository.deleteById(id);
    }






    }

