package com.example.academy.modules.topic.controller;

import com.example.academy.core.domain.request.topic.module.LessonRequest;
import com.example.academy.core.domain.response.module.LessonResponse;
import com.example.academy.modules.topic.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/lesson")
@RequiredArgsConstructor
public class LessonController {


    private final LessonService lessonService;

    @GetMapping
    public ResponseEntity<?> getAllLessons() {
        List<LessonResponse> lessons = lessonService.getAllLessons();
        return ResponseEntity.ok(lessons);
    }

    @GetMapping("/{lessonId}")
    public ResponseEntity<LessonResponse> getLessonById(@PathVariable Long lessonId) {
        LessonResponse lesson = lessonService.getLessonById(lessonId);
        return ResponseEntity.ok(lesson);
    }
    @PostMapping
    public ResponseEntity<LessonResponse> createLesson(@RequestBody LessonRequest lessonRequest) {
        LessonResponse lesson = lessonService.createLesson(lessonRequest);
        return ResponseEntity.ok(lesson);
    }

    @PutMapping("/{lessonId}")
    public ResponseEntity<LessonResponse> updateLesson(@PathVariable Long lessonId,
                                                       @RequestBody LessonRequest lessonRequest) {
        LessonResponse lesson = lessonService.updateLesson(lessonId, lessonRequest);
        return ResponseEntity.ok(lesson);
    }

    @DeleteMapping("/{lessonId}")
    public ResponseEntity<?> deleteLesson(@PathVariable Long lessonId) {
        lessonService.deleteLesson(lessonId);
        return ResponseEntity.noContent().build();
    }

}
