package com.example.academy.modules.test.service;

import com.example.academy.core.domain.request.test.QuestionRequest;
import com.example.academy.core.domain.response.test.QuestionResponse;

import java.util.List;

public interface QuestionService {
    QuestionResponse create(QuestionRequest request);
    List<QuestionResponse> getAll();
    QuestionResponse getById(Long id);
    QuestionResponse update(Long id, QuestionRequest request);
    void delete(Long id);
}

