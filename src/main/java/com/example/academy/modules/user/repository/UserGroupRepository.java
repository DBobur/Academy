package com.example.academy.modules.user.repository;

import com.example.academy.modules.user.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {
}
