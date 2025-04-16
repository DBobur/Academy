package com.example.academy.modules.topic.controller;

import com.example.academy.core.domain.request.module.ModuleRequest;
import com.example.academy.core.domain.response.user.ModuleResponse;
import com.example.academy.modules.topic.service.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/module")
@RequiredArgsConstructor
public class ModuleController {

    private final ModuleService moduleService;


    @GetMapping
    public ResponseEntity<List<ModuleResponse>> getAllModules() {
        List<ModuleResponse> moduleResponses = moduleService.getAllModules();
        return ResponseEntity.ok(moduleResponses);
    }

    @GetMapping("/{moduleId}")
    public ResponseEntity<ModuleResponse> getModuleById(@PathVariable Long moduleId) {
        ModuleResponse moduleResponse = moduleService.getModuleResponseById(moduleId);
        return ResponseEntity.ok(moduleResponse);
    }

    @PostMapping
    public ResponseEntity<ModuleResponse> createModule(@RequestBody ModuleRequest moduleRequest) {
        ModuleResponse moduleResponse = moduleService.createModule(moduleRequest);
        return ResponseEntity.ok(moduleResponse);
    }

    @PutMapping("/{moduleId}")
    public ResponseEntity<ModuleResponse> updateModule(@PathVariable Long moduleId, @RequestBody ModuleRequest moduleRequest) {
        ModuleResponse moduleResponse = moduleService.updateModule(moduleId, moduleRequest);
        return ResponseEntity.ok(moduleResponse);
    }

    @DeleteMapping("/{moduleId}")
    public ResponseEntity<?> deleteModule(@PathVariable Long moduleId) {
        moduleService.deleteModule(moduleId);
        return ResponseEntity.noContent().build();
    }



}
