package com.example.academy.modules.test.service;

import com.example.academy.core.domain.request.test.SubmissionRequest;
import com.example.academy.core.domain.response.test.SubmissionResponse;
import com.example.academy.modules.test.entity.OptionEntity;
import com.example.academy.modules.test.entity.QuestionEntity;
import com.example.academy.modules.test.entity.SubmissionEntity;
import com.example.academy.modules.test.repository.OptionRepository;
import com.example.academy.modules.test.repository.QuestionRepository;
import com.example.academy.modules.test.repository.SubmissionRepository;
import com.example.academy.modules.user.entity.UserEntity;
import com.example.academy.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubmissionServiceImpl implements SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;

    @Override
    public SubmissionResponse submitAnswer(SubmissionRequest request) {
        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        QuestionEntity question = questionRepository.findById(request.getQuestionId())
                .orElseThrow(() -> new RuntimeException("Question not found"));

        OptionEntity selectedOption = optionRepository.findById(request.getSelectedOptionId())
                .orElseThrow(() -> new RuntimeException("Option not found"));

        boolean isCorrect = selectedOption.isCorrect();

        SubmissionEntity submission = SubmissionEntity.builder()
                .user(user)
                .question(question)
                .selectedOption(selectedOption)
                .isCorrect(isCorrect)
                .build();

        return toResponse(submissionRepository.save(submission));
    }

    @Override
    public List<SubmissionResponse> getAll() {
        return submissionRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public SubmissionResponse getById(Long id) {
        return submissionRepository.findById(id).map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Submission not found"));
    }

    private SubmissionResponse toResponse(SubmissionEntity entity) {
        SubmissionResponse response = new SubmissionResponse();
        response.setId(entity.getId());
        response.setUserId(entity.getUser().getId());
        response.setQuestionId(entity.getQuestion().getId());
        response.setSelectedOptionId(entity.getSelectedOption().getId());
        response.setCorrect(entity.isCorrect());
        return response;
    }
}
