package com.example.academy.core.domain.request.topic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LessonRequest {
    private String title;
    private String content;
    private Long moduleId;
}
