package com.example.academy.modules.user.entity;

import com.example.academy.core.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "password_reset_tokens")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordResetToken extends BaseEntity {

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private LocalDateTime expiration;


}

