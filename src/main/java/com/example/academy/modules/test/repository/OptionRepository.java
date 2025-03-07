package com.example.academy.modules.test.repository;

import com.example.academy.modules.test.entity.OptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<OptionEntity, Long> {
}
