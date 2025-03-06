package com.example.academy.modules.user.repository;

import com.example.academy.modules.user.entity.UserEntity;
import com.example.academy.modules.user.enums.UserRole;
import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {
    Optional<UserEntity> findByIdAndIsDeleted(Long id, boolean isDeleted);
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByEmailOrNumber(String email, String number);
}
