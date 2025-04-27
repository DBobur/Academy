package com.example.academy.modules.topic.service;
import com.example.academy.core.domain.mapper.attandance.AttendanceDetailMapper;
import com.example.academy.core.domain.request.attendance.AttendanceDetailRequest;
import com.example.academy.core.domain.response.attendance.AttendanceDetailResponse;
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

    public AttendanceDetailResponse createAttendanceDetail(AttendanceDetailRequest request) {
        UserEntity student = userEntityRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        AttendanceDetailEntity entity = AttendanceDetailMapper.requestToEntity(request);
        AttendanceDetailEntity saved = attendanceDetailEntityRepository.save(entity);
        return AttendanceDetailMapper.entityToResponse(saved);
    }

    public List<AttendanceDetailResponse> getAllAttendanceDetails() {
        return attendanceDetailEntityRepository.findAll()
                .stream()
                .map(AttendanceDetailMapper::entityToResponse)
                .collect(Collectors.toList());
    }

    public AttendanceDetailResponse getAttendanceDetailById(Long id) {
        AttendanceDetailEntity entity = attendanceDetailEntityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance detail not found"));
        return AttendanceDetailMapper.entityToResponse(entity);
    }

    public AttendanceDetailResponse updateAttendanceDetail(Long id, AttendanceDetailRequest request) {
        AttendanceDetailEntity entity = attendanceDetailEntityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance detail not found"));

        UserEntity student = userEntityRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        entity.setDate(request.getDate());
        entity.setPresent(request.isPresent());
        entity.setAttendanceId(request.getAttendanceId());

        AttendanceDetailEntity updated = attendanceDetailEntityRepository.save(entity);
        return AttendanceDetailMapper.entityToResponse(updated);
    }

    public void deleteAttendanceDetail(Long id) {
        if (!attendanceDetailEntityRepository.existsById(id)) {
            throw new RuntimeException("Attendance detail not found");
        }
        attendanceDetailEntityRepository.deleteById(id);
    }






    }

