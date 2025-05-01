package com.example.academy.modules.user.entity;


import com.example.academy.core.common.BaseEntity;
import com.example.academy.modules.user.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity extends BaseEntity {

    private String fullName;

    // not null and unique
    @Column(nullable = false, unique = true)
    private String username;

    // not null
    @Column(nullable = false)
    private String password;

    // unique
    @Column(nullable = false, unique = true)
    private String email;

    // unique
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Wrong Format!")
    @Column(unique = true)
    private String number;


    private String address;

    private LocalDate dateOfBirth;

    private boolean isDeleted;

    // cascade All and fetch Eager
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private UserContract contracts;

    @Enumerated(EnumType.STRING)
    private UserRole role;

}

