package com.example.academy.modules.test.entity;

import com.example.academy.core.common.BaseEntity;
import com.example.academy.modules.user.entity.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(
        name = "submission"
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SubmissionEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuestionEntity question;

    @ManyToOne
    @JoinColumn(name = "option_id")
    private OptionEntity selectedOption;

    private boolean isCorrect;
}
