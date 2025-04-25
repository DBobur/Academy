package com.example.academy.modules.test.entity;

import com.example.academy.core.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(
        name = "tests"
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TestEntity extends BaseEntity {
    private String title;

    private Long subject;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<QuestionEntity> questions;
}
