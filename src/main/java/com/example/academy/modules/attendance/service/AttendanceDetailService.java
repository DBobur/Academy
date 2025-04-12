package com.example.academy.modules.attendance.service;

import com.example.academy.modules.attendance.entity.AttendanceDetailEntity;
import com.example.academy.modules.attendance.repository.AttendanceDetailRepository;
import com.example.academy.modules.user.entity.UserEntity;
import com.example.academy.modules.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AttendanceDetailService {

    private final AttendanceDetailRepository attendanceDetailRepository;
    private final UserRepository userRepository;

    public AttendanceDetailService(AttendanceDetailRepository attendanceDetailRepository, UserRepository userRepository) {
        this.attendanceDetailRepository = attendanceDetailRepository;
        this.userRepository = userRepository;
    }

    public AttendanceDetailEntity create(AttendanceDetailEntity attendanceDetailEntity){
        return attendanceDetailRepository.save(attendanceDetailEntity);
    }

    public void delete(AttendanceDetailEntity attendanceDetailEntity){
        attendanceDetailRepository.delete(attendanceDetailEntity);
    }

    public void deleteById(Long id){
        attendanceDetailRepository.deleteById(id);
    }

    public Optional<AttendanceDetailEntity> getById(Long id){
        return attendanceDetailRepository.findById(id);
    }
//todo check id in controller layer if present or not
    public List<AttendanceDetailEntity> getUserAllAttendanceDetails(Long userId){
        Optional<UserEntity> byId = userRepository.findById(userId);
        return attendanceDetailRepository.findALlByUserEntity(byId.get());
    }

    public List<AttendanceDetailEntity> getAbsentUserAttendance(Long userId){
        Optional<UserEntity> user = userRepository.findById(userId);
        return attendanceDetailRepository.findALlByUserEntity(user.get())
                .stream().filter(attendanceDetailEntity -> !attendanceDetailEntity.isPresent())
                .collect(Collectors.toList());
    }

    public List<AttendanceDetailEntity> getUserAttendances(Long userId){
        Optional<UserEntity> user = userRepository.findById(userId);
        return attendanceDetailRepository.findALlByUserEntity(user.get())
                .stream().filter(AttendanceDetailEntity::isPresent)
                .collect(Collectors.toList());
    }







}
