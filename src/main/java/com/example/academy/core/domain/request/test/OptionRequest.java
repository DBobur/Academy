package com.example.academy.core.domain.request.test;

import lombok.Data;

@Data
public class OptionRequest {
    private String optionText;
    private boolean isCorrect;
    private Long questionId;
}
