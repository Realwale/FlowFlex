package com.charistech.flowflex.backend.model.user;

import com.charistech.flowflex.backend.common.BaseEntity;
import com.charistech.flowflex.backend.constant.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@Builder
@Getter
@AllArgsConstructor
@Setter
@Entity
@Table(name = "users")
public class AppUser extends BaseEntity implements UserDetails {

    private String firstName;
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    private String phone;
    private String password;
    private boolean isVerified;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
