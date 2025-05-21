package com.example.academy.modules.attendance.entity.dto;

import com.example.academy.modules.attendance.entity.GroupEntity;
import com.example.academy.modules.topic.entity.ModuleEntity;
import com.example.academy.modules.topic.entity.dto.ModuleDto;
import com.example.academy.modules.user.entity.UserEntity;
import com.example.academy.modules.user.entity.dto.UserDto;
import lombok.Builder;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Builder
@Data
public class ScheduleDto {
    private Set<DayOfWeek> dayOfWeeks;
    private LocalDate startedDate;
    private LocalTime startTime;
    private ModuleDto module;
    private GroupDto group;
    private UserDto teacher;
}
