package com.example.academy.modules.user.entity;

import com.example.academy.core.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@EqualsAndHashCode
@Entity
@Table(
        name = "groups"
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserGroup extends BaseEntity {
    private String groupName;

    @ManyToMany
    @JoinTable(
            name = "group_users",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserEntity> users;
}
