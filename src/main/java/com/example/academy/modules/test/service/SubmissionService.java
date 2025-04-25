package com.example.academy.modules.test.service;


import com.example.academy.core.domain.request.test.SubmissionRequest;
import com.example.academy.core.domain.response.test.SubmissionResponse;

import java.util.List;

public interface SubmissionService {
    SubmissionResponse submitAnswer(SubmissionRequest request);
    List<SubmissionResponse> getAll();
    SubmissionResponse getById(Long id);
}

