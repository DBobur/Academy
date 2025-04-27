package com.example.academy.modules.topic.service;
import com.example.academy.core.domain.mapper.module.ModuleMapper;
import com.example.academy.core.domain.request.topic.ModuleRequest;
import com.example.academy.core.domain.response.module.ModuleResponse;

import com.example.academy.modules.topic.entity.ModuleEntity;
import com.example.academy.modules.topic.entity.TopicEntity;
import com.example.academy.modules.topic.repository.ModuleEntityRepository;
import com.example.academy.modules.topic.repository.TopicEntityRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModuleService {


    private final TopicEntityRepository topicEntityRepository;
    private final ModuleEntityRepository moduleEntityRepository;

    public ModuleResponse createModule(ModuleRequest moduleRequest) {
         TopicEntity topic = topicEntityRepository.findById(moduleRequest.getTopicId())
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        ModuleEntity moduleEntity = ModuleEntity.builder()
                .title(moduleRequest.getTitle())
                .description(moduleRequest.getDescription())
                .topic(topic)
                .build();

        ModuleEntity savedModuleEntity = moduleEntityRepository.save(moduleEntity);
        return ModuleMapper.entityToResponse(savedModuleEntity);
    }

    public List<ModuleResponse> getAllModules() {
        List<ModuleEntity> moduleEntities = moduleEntityRepository.findAll();
        return ModuleMapper.entitiesToResponses(moduleEntities);
    }

    public ModuleResponse getModuleResponseById(Long moduleId) {
        ModuleEntity moduleEntity = moduleEntityRepository.findById(moduleId)
                .orElseThrow(() -> new RuntimeException("Module not found"));
        return ModuleMapper.entityToResponse(moduleEntity);
    }


    public ModuleResponse updateModule(Long moduleId, ModuleRequest moduleRequest) {
        ModuleEntity moduleEntity = moduleEntityRepository.findById(moduleId)
                .orElseThrow(() -> new RuntimeException("Module not found"));

        TopicEntity topic = topicEntityRepository.findById(moduleRequest.getTopicId())
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        moduleEntity.setTitle(moduleRequest.getTitle());
        moduleEntity.setDescription(moduleRequest.getDescription());
        moduleEntity.setTopic(topic);
        ModuleEntity updatedModuleEntity = moduleEntityRepository.save(moduleEntity);

        return ModuleMapper.entityToResponse(updatedModuleEntity);
    }





    public void deleteModule(Long moduleId) {
        if (!moduleEntityRepository.existsById(moduleId)) {
            throw new RuntimeException("Module not found");
        }
        moduleEntityRepository.deleteById(moduleId);
    }


    }

