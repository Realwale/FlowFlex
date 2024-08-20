package com.charistech.flowflex.backend.security;

import com.charistech.flowflex.backend.exception.ResourceNotFoundException;
import com.charistech.flowflex.backend.model.user.AppUser;
import com.charistech.flowflex.backend.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CustomUserDetailService implements UserDetailsService {

    private final AppUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = userRepository.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException("Invalid email or password"));

        return new User(user.getEmail(), user.getPassword(), user.getAuthorities());
    }
}
