package com.example.academy.modules.test.entity;

import com.example.academy.core.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(
        name = "questions"
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class QuestionEntity extends BaseEntity {
    private String questionText;

    @ManyToOne
    private TestEntity test;

    @OneToMany
    private List<OptionEntity> options;

    // Constructors, Getters, Setters
}

