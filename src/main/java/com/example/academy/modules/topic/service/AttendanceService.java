package com.example.academy.modules.topic.service;
import com.example.academy.core.domain.mapper.attandance.AttendanceMapper;
import com.example.academy.core.domain.mapper.attandance.AttendanceWithDetailMapper;
import com.example.academy.core.domain.request.topic.module.attandance.AttendanceRequest;
import com.example.academy.core.domain.response.attandance.AttendanceResponse;
import com.example.academy.modules.attendance.entity.AttendanceDetailEntity;
import com.example.academy.modules.attendance.entity.AttendanceEntity;
import com.example.academy.modules.attendance.repository.AttendanceDetailEntityRepository;
import com.example.academy.modules.attendance.repository.AttendanceRepository;
import com.example.academy.modules.topic.entity.LessonEntity;
import com.example.academy.modules.topic.repository.LessonEntityRepository;
import com.example.academy.modules.user.entity.UserEntity;
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
        UserEntity user = userEntityRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        LessonEntity lesson = lessonEntityRepository.findById(request.getLessonId())
                .orElseThrow(() -> new RuntimeException("Lesson not found"));


        List<AttendanceDetailEntity> details = request.getAttendanceDetails().stream()
                .map(detailReq -> AttendanceWithDetailMapper.requestToEntity(detailReq, user))
                .collect(Collectors.toList());

        AttendanceEntity attendanceEntity = AttendanceMapper.requestToEntity(request, user, lesson, details);

        AttendanceEntity savedAttendance = attendanceRepository.save(attendanceEntity);


        for (AttendanceDetailEntity detail : details) {
            detail.setAttendance(savedAttendance); // set parent
            attendanceDetailEntityRepository.save(detail);
        }

        return AttendanceMapper.entityToResponse(savedAttendance);
    }


    public List<AttendanceResponse> getAllAttendances() {
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

        UserEntity user = userEntityRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        LessonEntity lesson = lessonEntityRepository.findById(request.getLessonId())
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        attendanceEntity.setUser(user);
        attendanceEntity.setLesson(lesson);

        attendanceEntity.getAttendanceDetails().clear();
        List<AttendanceDetailEntity> updatedDetails = request.getAttendanceDetails().stream()
                .map(detailReq -> {
                    AttendanceDetailEntity detailEntity = AttendanceWithDetailMapper.requestToEntity(detailReq, user);
                    detailEntity.setAttendance(attendanceEntity); // Set back-reference
                    return detailEntity;
                })
                .collect(Collectors.toList());

        attendanceEntity.getAttendanceDetails().addAll(updatedDetails);
        AttendanceEntity updatedAttendance = attendanceRepository.save(attendanceEntity);
        updatedDetails.forEach(attendanceDetailEntityRepository::save);

        return AttendanceMapper.entityToResponse(updatedAttendance);
    }


    public void deleteAttendance(Long id) {
        if (!attendanceRepository.existsById(id)) {
            throw new RuntimeException("Attendance not found");
        }
        attendanceRepository.deleteById(id);
    }






    }

