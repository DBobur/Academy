package com.example.academy.modules.test.controller;

import com.example.academy.core.domain.request.test.SubmissionRequest;
import com.example.academy.core.domain.response.test.SubmissionResponse;
import com.example.academy.modules.test.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/submissions")
@RequiredArgsConstructor
public class SubmissionController {

    private final SubmissionService submissionService;

    @PostMapping
    public SubmissionResponse submitAnswer(@RequestBody SubmissionRequest request) {
        return submissionService.submitAnswer(request);
    }

    @GetMapping
    public List<SubmissionResponse> getAll() {
        return submissionService.getAll();
    }

    @GetMapping("/{id}")
    public SubmissionResponse getById(@PathVariable Long id) {
        return submissionService.getById(id);
    }
}
