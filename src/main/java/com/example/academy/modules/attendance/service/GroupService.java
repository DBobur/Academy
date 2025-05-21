package com.example.academy.modules.attendance.service;

import com.example.academy.modules.attendance.entity.GroupEntity;
import com.example.academy.modules.attendance.entity.dto.GroupDto;
import com.example.academy.modules.attendance.entity.mapper.GroupMapper;
import com.example.academy.modules.attendance.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;

    public GroupDto saveGroup(GroupDto group) {
        GroupEntity groupEntity = GroupMapper.toEntity(group);
        return GroupMapper.toDtoWith(groupRepository.save(groupEntity));
    }
}
