package com.example.academy.modules.test.entity;

import com.example.academy.core.common.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
    @OneToMany
    private List<QuestionEntity> questions;
}
