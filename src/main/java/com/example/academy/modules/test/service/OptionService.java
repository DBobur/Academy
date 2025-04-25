package com.example.academy.modules.test.service;

import com.example.academy.core.domain.request.test.OptionRequest;
import com.example.academy.core.domain.response.test.OptionResponse;

import java.util.List;

public interface OptionService {
    OptionResponse create(OptionRequest request);
    List<OptionResponse> getAll();
    OptionResponse getById(Long id);
    OptionResponse update(Long id, OptionRequest request);
    void delete(Long id);
}

