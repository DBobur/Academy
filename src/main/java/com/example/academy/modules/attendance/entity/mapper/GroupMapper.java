package com.example.academy.modules.attendance.entity.mapper;


import com.example.academy.modules.attendance.entity.GroupEntity;
import com.example.academy.modules.attendance.entity.dto.GroupDto;

public class GroupMapper {
    public static GroupEntity toEntity(GroupDto group) {
        return new GroupEntity();
    }

    public static GroupDto toDtoWith(GroupEntity save) {
        return GroupDto.builder().build();
    }

    public static GroupDto toDto(GroupEntity save) {
        return null;
    }
}
