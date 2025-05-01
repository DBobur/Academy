package com.example.academy.modules.test.entity;

import com.example.academy.core.common.BaseEntity;
import jakarta.persistence.*;
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

    @ManyToOne(fetch = FetchType.EAGER)
    private QuestionEntity question;
}

