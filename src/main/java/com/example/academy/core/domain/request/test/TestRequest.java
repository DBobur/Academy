package com.example.academy.core.domain.request.test;
import lombok.Data;
import java.util.List;

@Data
public class TestRequest {
    private String title;
    private Long subject;
    private List<Long> questionIds;
}
