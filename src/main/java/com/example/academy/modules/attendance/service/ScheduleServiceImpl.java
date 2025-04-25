package com.example.academy.modules.attendance.service;


import com.example.academy.core.domain.request.attendance.ScheduleRequest;
import com.example.academy.core.domain.response.attendance.ScheduleResponse;
import com.example.academy.modules.attendance.entity.ScheduleEntity;
import com.example.academy.modules.attendance.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Override
    public ScheduleResponse create(ScheduleRequest request) {
        ScheduleEntity schedule = ScheduleEntity.builder()
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .daysOfWeek(request.getDaysOfWeek())
                .startTime(request.getStartTime())
                .groupId(request.getGroupId())
                .moduleId(request.getModuleId())
                .teacherId(request.getTeacherId())
                .build();

        return toResponse(scheduleRepository.save(schedule));
    }

    @Override
    public ScheduleResponse update(Long id, ScheduleRequest request) {
        ScheduleEntity schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));

        schedule.setStartDate(request.getStartDate());
        schedule.setEndDate(request.getEndDate());
        schedule.setDaysOfWeek(request.getDaysOfWeek());
        schedule.setStartTime(request.getStartTime());
        schedule.setGroupId(request.getGroupId());
        schedule.setModuleId(request.getModuleId());
        schedule.setTeacherId(request.getTeacherId());

        return toResponse(scheduleRepository.save(schedule));
    }

    @Override
    public ScheduleResponse getById(Long id) {
        return scheduleRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
    }

    @Override
    public List<ScheduleResponse> getAll() {
        return scheduleRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        scheduleRepository.deleteById(id);
    }

    private ScheduleResponse toResponse(ScheduleEntity entity) {
        ScheduleResponse response = new ScheduleResponse();
        response.setId(entity.getId());
        response.setStartDate(entity.getStartDate());
        response.setEndDate(entity.getEndDate());
        response.setDaysOfWeek(entity.getDaysOfWeek());
        response.setStartTime(entity.getStartTime());
        response.setGroupId(entity.getGroupId());
        response.setModuleId(entity.getModuleId());
        response.setTeacherId(entity.getTeacherId());
        return response;
    }
}
