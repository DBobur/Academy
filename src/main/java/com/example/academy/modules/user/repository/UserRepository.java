package com.example.academy.modules.user.repository;

import com.example.academy.modules.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmailOrNumber(String identifier, String identifier1);

    Optional<UserEntity> findByIdAndIsDeleted(Long id, boolean b);
}
