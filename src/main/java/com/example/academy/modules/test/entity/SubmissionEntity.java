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
    private UserEntity user;

    @ManyToOne
    private QuestionEntity question;

    @ManyToOne
    private OptionEntity selectedOption;

    private boolean isCorrect;
}
