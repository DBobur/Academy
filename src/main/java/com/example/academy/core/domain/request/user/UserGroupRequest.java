package com.example.academy.core.domain.request.user;

import lombok.Data;

import java.util.List;

@Data
public class UserGroupRequest {
    private String groupName;
    private List<Long> userIds;
}

