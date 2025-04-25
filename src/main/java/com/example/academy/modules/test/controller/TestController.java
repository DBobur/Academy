package com.example.academy.modules.test.controller;

import com.example.academy.core.domain.request.test.TestRequest;
import com.example.academy.core.domain.response.test.TestResponse;
import com.example.academy.modules.test.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tests")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @PostMapping
    public TestResponse create(@RequestBody TestRequest request) {
        return testService.create(request);
    }

    @GetMapping
    public List<TestResponse> getAll() {
        return testService.getAll();
    }

    @GetMapping("/{id}")
    public TestResponse getById(@PathVariable Long id) {
        return testService.getById(id);
    }

    @PutMapping("/{id}")
    public TestResponse update(@PathVariable Long id, @RequestBody TestRequest request) {
        return testService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        testService.delete(id);
    }
}
