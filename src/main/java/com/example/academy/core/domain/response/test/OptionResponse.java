package com.example.academy.core.domain.response.test;

import lombok.Data;

@Data
public class OptionResponse {
    private Long id;
    private String optionText;
    private boolean isCorrect;
    private Long questionId;
}

