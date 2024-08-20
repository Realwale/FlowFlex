package com.charistech.flowflex.backend.model.token;

import com.charistech.flowflex.backend.common.BaseEntity;
import com.charistech.flowflex.backend.model.user.AppUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "jwt_token")
public class JwtToken extends BaseEntity {

    @Column(name = "token", unique = true)
    private String token;
    @Column(unique = true)
    private String refreshToken;
    private boolean isExpired;
    private boolean isRevoked;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private AppUser user;

    @Column(name = "expiry_date")
    private Date expiryDate;
}