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

    @ManyToOne(fetch = FetchType.EAGER)
    private TestEntity test;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OptionEntity> options;
}

