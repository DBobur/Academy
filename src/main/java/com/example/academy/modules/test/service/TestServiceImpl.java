package com.example.academy.modules.test.service;

import com.example.academy.core.domain.request.test.TestRequest;
import com.example.academy.core.domain.response.test.TestResponse;
import com.example.academy.modules.test.entity.QuestionEntity;
import com.example.academy.modules.test.entity.TestEntity;
import com.example.academy.modules.test.repository.QuestionRepository;
import com.example.academy.modules.test.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;
    private final QuestionRepository questionRepository;

    @Override
    public TestResponse create(TestRequest request) {
        List<QuestionEntity> questions = questionRepository.findAllById(request.getQuestionIds());

        TestEntity test = TestEntity.builder()
                .title(request.getTitle())
                .subject(request.getSubject())
                .questions(questions)
                .build();

        TestEntity saved = testRepository.save(test);
        return toResponse(saved);
    }

    @Override
    public List<TestResponse> getAll() {
        return testRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TestResponse getById(Long id) {
        return testRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Test not found"));
    }

    @Override
    public TestResponse update(Long id, TestRequest request) {
        TestEntity test = testRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Test not found"));

        test.setTitle(request.getTitle());
        test.setSubject(request.getSubject());
        test.setQuestions(questionRepository.findAllById(request.getQuestionIds()));

        return toResponse(testRepository.save(test));
    }

    @Override
    public void delete(Long id) {
        testRepository.deleteById(id);
    }

    private TestResponse toResponse(TestEntity entity) {
        TestResponse response = new TestResponse();
        response.setId(entity.getId());
        response.setTitle(entity.getTitle());
        response.setSubject(entity.getSubject());
        response.setQuestionIds(entity.getQuestions().stream().map(QuestionEntity::getId).collect(Collectors.toList()));
        return response;
    }
}
