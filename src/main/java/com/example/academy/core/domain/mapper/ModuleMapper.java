package com.example.academy.core.domain.mapper;

import com.example.academy.core.domain.request.module.ModuleRequest;
import com.example.academy.core.domain.response.user.ModuleResponse;
import com.example.academy.modules.topic.entity.ModuleEntity;
import com.example.academy.modules.topic.entity.TopicEntity;
import com.example.academy.modules.topic.repository.TopicEntityRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ModuleMapper {

    public static ModuleEntity requestToModuleEntity(ModuleRequest moduleRequest, TopicEntityRepository topicEntityRepository) {
        TopicEntity topic = topicEntityRepository.findById(moduleRequest.getTopicId())
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        return ModuleEntity.builder()
                .title(moduleRequest.getTitle())
                .description(moduleRequest.getDescription())
                .topic(topic)
                .build();
    }


        public static ModuleResponse entityToResponse(ModuleEntity moduleEntity) {
            return ModuleResponse.builder()
                    .id(moduleEntity.getId())
                    .createdDate(moduleEntity.getCreatedTime())
                    .updatedDate(moduleEntity.getUpdatedTime())
                    .title(moduleEntity.getTitle())
                    .description(moduleEntity.getDescription())
                    .topicId(moduleEntity.getTopic().getId())
                    .topicName(moduleEntity.getTopic().getName())
                    .lessons(moduleEntity.getLessons())
                    .build();
        }

    public static List<ModuleResponse> entitiesToResponses(List<ModuleEntity> moduleEntities) {
        return moduleEntities.stream()
                .map(ModuleMapper::entityToResponse)
                .collect(Collectors.toList());
    }

    }

