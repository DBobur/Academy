package com.example.academy.modules.test.controller;

import com.example.academy.core.domain.request.test.OptionRequest;
import com.example.academy.core.domain.response.test.OptionResponse;
import com.example.academy.modules.test.service.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/options")
@RequiredArgsConstructor
public class OptionController {

    private final OptionService optionService;

    @PostMapping
    public OptionResponse create(@RequestBody OptionRequest request) {
        return optionService.create(request);
    }

    @GetMapping
    public List<OptionResponse> getAll() {
        return optionService.getAll();
    }

    @GetMapping("/{id}")
    public OptionResponse getById(@PathVariable Long id) {
        return optionService.getById(id);
    }

    @PutMapping("/{id}")
    public OptionResponse update(@PathVariable Long id, @RequestBody OptionRequest request) {
        return optionService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        optionService.delete(id);
    }
}

