package com.example.academy.modules.user.entity;


import com.example.academy.core.common.BaseEntity;
import com.example.academy.modules.user.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import java.time.LocalDate;


@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity extends BaseEntity {

    private String fullName;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Wrong Format!")
    @Column(unique = true)
    private String number;

    private String address;

    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private boolean isDeleted = false;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}

