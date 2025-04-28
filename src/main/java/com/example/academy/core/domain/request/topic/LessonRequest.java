package com.example.academy.core.domain.request.topic;


import lombok.Data;


@Data
public class LessonRequest {
    private String title;
    private String content;
    private Long moduleId;
}
