package com.example.academy.core.domain.specific;

import com.example.academy.modules.user.entity.UserEntity;
import com.example.academy.modules.user.enums.UserRole;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecifications {
    public static Specification<UserEntity> isActive(boolean active) {
        return (root, query, cb) -> cb.equal(root.get("isDeleted"), !active);
    }

    public static Specification<UserEntity> hasRoleName(UserRole roleName) {
        return (root, query, cb) -> cb.equal(root.get("role"), roleName);
    }
}

