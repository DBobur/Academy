package com.example.academy.core.domain.response.user;

import lombok.Data;

import java.util.List;

@Data
public class UserGroupResponse {
    private Long id;
    private String groupName;
    private List<Long> userIds;
}

