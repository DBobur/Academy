package com.example.academy.modules.user.service;

import com.example.academy.core.domain.request.user.UserGroupRequest;
import com.example.academy.core.domain.response.user.UserGroupResponse;
import com.example.academy.modules.user.entity.UserEntity;
import com.example.academy.modules.user.entity.UserGroup;
import com.example.academy.modules.user.repository.UserGroupRepository;
import com.example.academy.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserGroupServiceImpl implements UserGroupService {

    private final UserGroupRepository groupRepository;
    private final UserRepository userRepository;

    @Override
    public UserGroupResponse create(UserGroupRequest request) {
        List<UserEntity> users = userRepository.findAllById(request.getUserIds());

        UserGroup group = UserGroup.builder()
                .groupName(request.getGroupName())
                .users(users)
                .build();

        return toResponse(groupRepository.save(group));
    }

    @Override
    public UserGroupResponse update(Long id, UserGroupRequest request) {
        UserGroup group = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        List<UserEntity> users = userRepository.findAllById(request.getUserIds());

        group.setGroupName(request.getGroupName());
        group.setUsers(users);

        return toResponse(groupRepository.save(group));
    }

    @Override
    public UserGroupResponse getById(Long id) {
        return groupRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Group not found"));
    }

    @Override
    public List<UserGroupResponse> getAll() {
        return groupRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        groupRepository.deleteById(id);
    }

    private UserGroupResponse toResponse(UserGroup group) {
        UserGroupResponse response = new UserGroupResponse();
        response.setId(group.getId());
        response.setGroupName(group.getGroupName());
        response.setUserIds(group.getUsers().stream().map(UserEntity::getId).collect(Collectors.toList()));
        return response;
    }
}

