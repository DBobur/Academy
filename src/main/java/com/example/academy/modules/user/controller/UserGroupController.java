package com.example.academy.modules.user.controller;


import com.example.academy.core.domain.request.user.UserGroupRequest;
import com.example.academy.core.domain.response.user.UserGroupResponse;
import com.example.academy.modules.user.service.UserGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class UserGroupController {

    private final UserGroupService groupService;

    @PostMapping
    public UserGroupResponse create(@RequestBody UserGroupRequest request) {
        return groupService.create(request);
    }

    @GetMapping
    public List<UserGroupResponse> getAll() {
        return groupService.getAll();
    }

    @GetMapping("/{id}")
    public UserGroupResponse getById(@PathVariable Long id) {
        return groupService.getById(id);
    }

    @PutMapping("/{id}")
    public UserGroupResponse update(@PathVariable Long id, @RequestBody UserGroupRequest request) {
        return groupService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        groupService.delete(id);
    }
}

