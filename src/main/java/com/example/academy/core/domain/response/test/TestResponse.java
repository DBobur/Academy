package com.example.academy.core.domain.response.test;

import lombok.Data;
import java.util.List;

@Data
public class TestResponse {
    private Long id;
    private String title;
    private Long subject;
    private List<Long> questionIds;
}
