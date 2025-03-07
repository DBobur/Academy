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
        name = "topic"
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TopicEntity extends BaseEntity {
    private String title;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    private List<QuestionEntity> questions;
}
