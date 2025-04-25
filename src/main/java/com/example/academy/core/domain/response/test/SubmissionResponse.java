package com.example.academy.core.domain.response.test;

import lombok.Data;

@Data
public class SubmissionResponse {
    private Long id;
    private Long userId;
    private Long questionId;
    private Long selectedOptionId;
    private boolean isCorrect;
}

