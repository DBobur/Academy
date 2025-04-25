package com.example.academy.core.domain.response.test;

import lombok.Data;

import java.util.List;

@Data
public class QuestionResponse {
    private Long id;
    private String questionText;
    private Long testId;
    private List<Long> optionIds;
}

