package com.charistech.flowflex.backend.repository;

import com.charistech.flowflex.backend.model.token.JwtToken;
import com.charistech.flowflex.backend.model.user.AppUser;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends BaseEntityJpaRepository<JwtToken>{
    boolean existsByUser(AppUser appUser);

    JwtToken findByUser(AppUser appUser);

    Optional<JwtToken> findByToken(String token);
}
