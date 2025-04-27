package com.example.academy.modules.topic.service;
import com.example.academy.core.domain.mapper.attandance.AttendanceMapper;
import com.example.academy.core.domain.request.attendance.AttendanceRequest;
import com.example.academy.core.domain.response.attendance.AttendanceResponse;
import com.example.academy.modules.attendance.entity.AttendanceEntity;
import com.example.academy.modules.attendance.repository.AttendanceDetailEntityRepository;
import com.example.academy.modules.attendance.repository.AttendanceRepository;
import com.example.academy.modules.topic.repository.LessonEntityRepository;
import com.example.academy.modules.user.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final UserEntityRepository userEntityRepository;
    private final LessonEntityRepository lessonEntityRepository;
    private final AttendanceRepository attendanceRepository;
    private final AttendanceDetailEntityRepository attendanceDetailEntityRepository;

    public AttendanceResponse createAttendance(AttendanceRequest request) {
        AttendanceEntity attendanceEntity = AttendanceMapper.requestToEntity(request);

        AttendanceEntity savedAttendance = attendanceRepository.save(attendanceEntity);

        return AttendanceMapper.entityToResponse(savedAttendance);
    }


    public List<com.example.academy.core.domain.response.attendance.AttendanceResponse> getAllAttendances() {
        return attendanceRepository.findAll()
                .stream()
                .map(AttendanceMapper::entityToResponse)
                .collect(Collectors.toList());
    }


    public AttendanceResponse getAttendanceById(Long id) {
        AttendanceEntity attendanceEntity = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));
        return AttendanceMapper.entityToResponse(attendanceEntity);
    }


    public AttendanceResponse updateAttendance(Long id, AttendanceRequest request) {
        AttendanceEntity attendanceEntity = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        AttendanceEntity updatedAttendance = attendanceRepository.save(attendanceEntity);
        return AttendanceMapper.entityToResponse(updatedAttendance);
    }


    public void deleteAttendance(Long id) {
        if (!attendanceRepository.existsById(id)) {
            throw new RuntimeException("Attendance not found");
        }
        attendanceRepository.deleteById(id);
    }






    }

