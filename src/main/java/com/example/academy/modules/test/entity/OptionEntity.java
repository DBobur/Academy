package com.example.academy.modules.test.entity;

import com.example.academy.core.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(
        name = "option"
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OptionEntity extends BaseEntity {
    private String optionText;
    private boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuestionEntity question;
}

