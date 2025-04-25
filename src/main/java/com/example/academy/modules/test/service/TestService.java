package com.example.academy.modules.test.service;

import com.example.academy.core.domain.request.test.TestRequest;
import com.example.academy.core.domain.response.test.TestResponse;

import java.util.List;

public interface TestService {
    TestResponse create(TestRequest request);
    List<TestResponse> getAll();
    TestResponse getById(Long id);
    TestResponse update(Long id, TestRequest request);
    void delete(Long id);
}

