package com.example.academy.modules.test.entity;

import com.example.academy.core.common.BaseEntity;
import com.example.academy.modules.user.entity.UserEntity;
import jakarta.persistence.*;
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
    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.EAGER)
    private QuestionEntity question;

    @ManyToOne(fetch = FetchType.EAGER)
    private OptionEntity selectedOption;

    private boolean isCorrect;
}
