package com.example.academy.core.domain.request.test;

import lombok.Data;

@Data
public class QuestionRequest {
    private String questionText;
    private Long testId;
}

