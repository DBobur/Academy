package com.example.academy.modules.topic.service;
import com.example.academy.core.domain.mapper.attandance.ScheduleMapper;
import com.example.academy.core.domain.request.topic.module.attandance.ScheduleRequest;
import com.example.academy.core.domain.response.attandance.ScheduleResponse;
import com.example.academy.modules.attendance.entity.ScheduleEntity;
import com.example.academy.modules.attendance.repository.ScheduleRepository;
import com.example.academy.modules.topic.entity.ModuleEntity;
import com.example.academy.modules.topic.repository.ModuleEntityRepository;
import com.example.academy.modules.user.entity.UserEntity;
import com.example.academy.modules.user.entity.UserGroup;
import com.example.academy.modules.user.repository.UserEntityRepository;
import com.example.academy.modules.user.repository.UserGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor

public class ScheduleService {

    private final ModuleEntityRepository moduleEntityRepository;
    private final UserEntityRepository userEntityRepository;
    private final UserGroupRepository userGroupRepository;
    private final ScheduleRepository scheduleRepository;

    public ScheduleResponse createSchedule(ScheduleRequest request) {
        ModuleEntity module = moduleEntityRepository.findById(request.getModuleId())
                .orElseThrow(() -> new RuntimeException("Module not found"));

        UserEntity teacher = userEntityRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        UserGroup group = userGroupRepository.findById(request.getGroupId())
                .orElseThrow(() -> new RuntimeException("Group not found"));

        ScheduleEntity schedule = ScheduleMapper.requestToEntity(request, group, module, teacher);
        ScheduleEntity saved = scheduleRepository.save(schedule);
        return ScheduleMapper.entityToResponse(saved);
    }

    public List<ScheduleResponse> getAllSchedules() {
        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleMapper::entityToResponse)
                .collect(Collectors.toList());
    }


    public ScheduleResponse getScheduleById(Long id) {
        ScheduleEntity schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
        return ScheduleMapper.entityToResponse(schedule);
    }


    public ScheduleResponse updateSchedule(Long id, ScheduleRequest request) {
        ScheduleEntity schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));

        ModuleEntity module = moduleEntityRepository.findById(request.getModuleId())
                .orElseThrow(() -> new RuntimeException("Module not found"));

        UserEntity teacher = userEntityRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        UserGroup group = userGroupRepository.findById(request.getGroupId())
                .orElseThrow(() -> new RuntimeException("Group not found"));

        schedule.setDayOfWeek(request.getDayOfWeek());
        schedule.setStartTime(request.getStartTime());
        schedule.setEndTime(request.getEndTime());
        schedule.setModule(module);
        schedule.setGroup(group);
        schedule.setTeacher(teacher);

        ScheduleEntity updated = scheduleRepository.save(schedule);
        return ScheduleMapper.entityToResponse(updated);
    }


    public void deleteSchedule(Long id) {
        if (!scheduleRepository.existsById(id)) {
            throw new RuntimeException("Schedule not found");
        }
        scheduleRepository.deleteById(id);
    }
}






