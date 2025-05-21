package com.example.academy.modules.attendance.entity.dto;

import com.example.academy.modules.user.entity.dto.UserDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Builder
@Data
public class GroupDto {

    @NotBlank
    private String groupName;
    private String subjectName;
    @NotBlank
    private UserDto user;
    private LocalTime startTime;
    private List<UserDto> teachers;
    private List<UserDto> users;
    private List<ScheduleDto> schedules;
}
