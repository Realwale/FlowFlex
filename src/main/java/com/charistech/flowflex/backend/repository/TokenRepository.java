package com.charistech.flowflex.backend.repository;

import com.charistech.flowflex.backend.model.token.JwtToken;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends BaseEntityJpaRepository<JwtToken>{
}
