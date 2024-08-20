package com.charistech.flowflex.backend.repository;

import com.charistech.flowflex.backend.common.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseEntityJpaRepository <T extends BaseEntity> extends JpaRepository<T, Long> {
}