package com.example.academy.modules.test.service;

import com.example.academy.core.domain.request.test.QuestionRequest;
import com.example.academy.core.domain.response.test.QuestionResponse;
import com.example.academy.modules.test.entity.OptionEntity;
import com.example.academy.modules.test.entity.QuestionEntity;
import com.example.academy.modules.test.entity.TestEntity;
import com.example.academy.modules.test.repository.OptionRepository;
import com.example.academy.modules.test.repository.QuestionRepository;
import com.example.academy.modules.test.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final TestRepository testRepository;
    private final OptionRepository optionRepository;

    @Override
    public QuestionResponse create(QuestionRequest request) {
        TestEntity test = testRepository.findById(request.getTestId())
                .orElseThrow(() -> new RuntimeException("Test not found"));

        QuestionEntity question = QuestionEntity.builder()
                .questionText(request.getQuestionText())
                .test(test)
                .build();

        return toResponse(questionRepository.save(question));
    }

    @Override
    public List<QuestionResponse> getAll() {
        return questionRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public QuestionResponse getById(Long id) {
        return questionRepository.findById(id).map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Question not found"));
    }

    @Override
    public QuestionResponse update(Long id, QuestionRequest request) {
        QuestionEntity question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        question.setQuestionText(request.getQuestionText());
        question.setTest(testRepository.findById(request.getTestId())
                .orElseThrow(() -> new RuntimeException("Test not found")));

        return toResponse(questionRepository.save(question));
    }

    @Override
    public void delete(Long id) {
        questionRepository.deleteById(id);
    }

    private QuestionResponse toResponse(QuestionEntity entity) {
        QuestionResponse response = new QuestionResponse();
        response.setId(entity.getId());
        response.setQuestionText(entity.getQuestionText());
        response.setTestId(entity.getTest().getId());
        response.setOptionIds(entity.getOptions() == null ? List.of() :
                entity.getOptions().stream().map(OptionEntity::getId).collect(Collectors.toList()));
        return response;
    }
}

