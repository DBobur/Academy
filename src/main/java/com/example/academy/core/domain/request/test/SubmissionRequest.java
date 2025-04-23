package com.example.academy.core.domain.request.test;

import lombok.Data;

@Data
public class SubmissionRequest {
    private Long userId;
    private Long questionId;
    private Long selectedOptionId;
}

