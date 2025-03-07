package com.example.academy.modules.test.entity;

import com.example.academy.core.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(
        name = "question"
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class QuestionEntity extends BaseEntity {
    private String questionText;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private TopicEntity topic;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<OptionEntity> options;

    // Constructors, Getters, Setters
}

