package com.example.academy.modules.test.controller;

import com.example.academy.core.domain.request.test.QuestionRequest;
import com.example.academy.core.domain.response.test.QuestionResponse;
import com.example.academy.modules.test.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping
    public QuestionResponse create(@RequestBody QuestionRequest request) {
        return questionService.create(request);
    }

    @GetMapping
    public List<QuestionResponse> getAll() {
        return questionService.getAll();
    }

    @GetMapping("/{id}")
    public QuestionResponse getById(@PathVariable Long id) {
        return questionService.getById(id);
    }

    @PutMapping("/{id}")
    public QuestionResponse update(@PathVariable Long id, @RequestBody QuestionRequest request) {
        return questionService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        questionService.delete(id);
    }
}

