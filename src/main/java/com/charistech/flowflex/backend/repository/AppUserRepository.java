package com.charistech.flowflex.backend.repository;

import com.charistech.flowflex.backend.model.user.AppUser;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends BaseEntityJpaRepository<AppUser> {
    Optional<AppUser> findByEmail(String email);

    boolean existsByEmail(String email);
}
