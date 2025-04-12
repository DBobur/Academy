package com.example.academy.modules.attendance.service;

import com.example.academy.modules.attendance.entity.ScheduleEntity;
import com.example.academy.modules.attendance.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleEntity createScheduleEntity(ScheduleEntity scheduleEntity){
        return scheduleRepository.save(scheduleEntity);
    }

    public void deleteScheduleEntity(ScheduleEntity scheduleEntity){
        scheduleRepository.delete(scheduleEntity);
    }

    public void deleteScheduleEntityById(Long id){
        scheduleRepository.deleteById(id);
    }

    public Optional<ScheduleEntity> getById(Long id){
        return scheduleRepository.findById(id);
    }

}
