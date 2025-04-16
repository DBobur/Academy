package com.example.academy.modules.user.entity;

import com.example.academy.core.common.BaseEntity;
import com.example.academy.modules.user.enums.ContractStatus;
import com.example.academy.modules.user.enums.ContractType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "user_contracts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserContract extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    private ContractType contractTy;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private ContractStatus status;

    @Lob
    private String contractDetails;

   /* @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContractDocument> documents;*/

}
