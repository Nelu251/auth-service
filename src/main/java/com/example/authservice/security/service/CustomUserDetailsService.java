package com.example.authservice.security.service;


import com.example.authservice.domain.model.User;
import com.example.authservice.security.model.CustomUserDetails;
import com.example.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User with email: " + email + "not found");
        }

        log.info("IN loadUserByUsername - user with email: {} successfully loaded", email);
        return CustomUserDetails.fromUserToCustomUserDetails(user);
    }
}
