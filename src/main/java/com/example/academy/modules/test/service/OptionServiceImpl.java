package com.example.academy.modules.test.service;

import com.example.academy.core.domain.request.test.OptionRequest;
import com.example.academy.core.domain.response.test.OptionResponse;
import com.example.academy.modules.test.entity.OptionEntity;
import com.example.academy.modules.test.entity.QuestionEntity;
import com.example.academy.modules.test.repository.OptionRepository;
import com.example.academy.modules.test.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OptionServiceImpl implements OptionService {

    private final OptionRepository optionRepository;
    private final QuestionRepository questionRepository;

    @Override
    public OptionResponse create(OptionRequest request) {
        QuestionEntity question = questionRepository.findById(request.getQuestionId())
                .orElseThrow(() -> new RuntimeException("Question not found"));

        OptionEntity option = OptionEntity.builder()
                .optionText(request.getOptionText())
                .isCorrect(request.isCorrect())
                .question(question)
                .build();

        return toResponse(optionRepository.save(option));
    }

    @Override
    public List<OptionResponse> getAll() {
        return optionRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public OptionResponse getById(Long id) {
        return optionRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Option not found"));
    }

    @Override
    public OptionResponse update(Long id, OptionRequest request) {
        OptionEntity option = optionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Option not found"));

        QuestionEntity question = questionRepository.findById(request.getQuestionId())
                .orElseThrow(() -> new RuntimeException("Question not found"));

        option.setOptionText(request.getOptionText());
        option.setCorrect(request.isCorrect());
        option.setQuestion(question);

        return toResponse(optionRepository.save(option));
    }

    @Override
    public void delete(Long id) {
        optionRepository.deleteById(id);
    }

    private OptionResponse toResponse(OptionEntity entity) {
        OptionResponse response = new OptionResponse();
        response.setId(entity.getId());
        response.setOptionText(entity.getOptionText());
        response.setCorrect(entity.isCorrect());
        response.setQuestionId(entity.getQuestion().getId());
        return response;
    }
}

