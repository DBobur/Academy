package com.example.academy.modules.attendance.controller;

import com.example.academy.modules.attendance.entity.dto.GroupDto;
import com.example.academy.modules.attendance.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/group")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @PostMapping
    public ResponseEntity<?> createGroup(@RequestBody GroupDto group) {
        return ResponseEntity.ok(groupService.saveGroup(group));
    }
}
