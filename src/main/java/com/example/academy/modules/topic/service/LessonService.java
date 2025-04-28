package com.example.academy.modules.topic.service;
import com.example.academy.core.domain.mapper.module.LessonMapper;
import com.example.academy.core.domain.request.topic.LessonRequest;
import com.example.academy.core.domain.response.module.LessonResponse;
import com.example.academy.modules.topic.entity.LessonEntity;
import com.example.academy.modules.topic.entity.ModuleEntity;
import com.example.academy.modules.topic.repository.LessonEntityRepository;
import com.example.academy.modules.topic.repository.ModuleEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class LessonService {



    private final ModuleEntityRepository moduleEntityRepository;
    private final LessonEntityRepository lessonEntityRepository;


    public LessonResponse createLesson(LessonRequest lessonRequest) {
        LessonEntity lessonEntity = LessonMapper.requestToLessonEntity(lessonRequest, moduleEntityRepository);
        LessonEntity savedLesson = lessonEntityRepository.save(lessonEntity);
        return LessonMapper.entityToResponse(savedLesson);
    }

    public List<LessonResponse> getAllLessons() {
        List<LessonEntity> lessons = lessonEntityRepository.findAll();
        return lessons.stream()
                .map(LessonMapper::entityToResponse)
                .collect(Collectors.toList());
    }

    public LessonResponse getLessonById(Long lessonId) {
        LessonEntity lesson = lessonEntityRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));
        return LessonMapper.entityToResponse(lesson);
    }
    public LessonResponse updateLesson(Long lessonId, LessonRequest lessonRequest) {
        LessonEntity lesson = lessonEntityRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        ModuleEntity module = moduleEntityRepository.findById(lessonRequest.getModuleId())
                .orElseThrow(() -> new RuntimeException("Module not found"));

        lesson.setTitle(lessonRequest.getTitle());
        lesson.setContent(lessonRequest.getContent());
        lesson.setModuleId(module.getId());

        LessonEntity updatedLesson = lessonEntityRepository.save(lesson);
        return LessonMapper.entityToResponse(updatedLesson);
    }

    public void deleteLesson(Long lessonId) {
        if (!lessonEntityRepository.existsById(lessonId)) {
            throw new RuntimeException("Lesson not found");
        }
        lessonEntityRepository.deleteById(lessonId);
    }



    }

