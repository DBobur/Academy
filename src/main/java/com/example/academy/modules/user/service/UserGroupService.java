package com.example.academy.modules.user.service;



import com.example.academy.core.domain.request.user.UserGroupRequest;
import com.example.academy.core.domain.response.user.UserGroupResponse;

import java.util.List;

public interface UserGroupService {
    UserGroupResponse create(UserGroupRequest request);
    UserGroupResponse update(Long id, UserGroupRequest request);
    UserGroupResponse getById(Long id);
    List<UserGroupResponse> getAll();
    void delete(Long id);
}

