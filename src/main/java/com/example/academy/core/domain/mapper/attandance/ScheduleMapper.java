package com.example.academy.core.domain.mapper.attandance;

import com.example.academy.core.domain.request.topic.module.attandance.ScheduleRequest;
import com.example.academy.core.domain.response.attandance.ScheduleResponse;
import com.example.academy.modules.attendance.entity.ScheduleEntity;
import com.example.academy.modules.topic.entity.ModuleEntity;
import com.example.academy.modules.user.entity.UserEntity;
import com.example.academy.modules.user.entity.UserGroup;

public class ScheduleMapper {
    public static ScheduleEntity requestToEntity(ScheduleRequest request, UserGroup group, ModuleEntity module, UserEntity teacher) {
        return ScheduleEntity.builder()
                .dayOfWeek(request.getDayOfWeek())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .group(group)
                .module(module)
                .teacher(teacher)
                .build();
    }

    public static ScheduleResponse entityToResponse(ScheduleEntity entity) {
        return ScheduleResponse.builder()
                .id(entity.getId())
                .dayOfWeek(entity.getDayOfWeek())
                .startTime(entity.getStartTime())
                .endTime(entity.getEndTime())
                .groupId(entity.getGroup().getId())
                .groupName(entity.getGroup().getGroupName())
                .moduleId(entity.getModule().getId())
                .moduleTitle(entity.getModule().getTitle())
                .teacherId(entity.getTeacher().getId())
                .teacherFullName(entity.getTeacher().getFullName())
                .createdDate(entity.getCreatedTime())
                .updatedDate(entity.getUpdatedTime())
                .build();
    }
}
