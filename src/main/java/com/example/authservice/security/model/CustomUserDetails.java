package com.example.authservice.security.model;

import com.example.authservice.domain.model.User;
import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

    private Long id;

    private String email;

    private String password;

    private String name;

    private Collection<? extends GrantedAuthority> grantedAuthorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
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

    public static CustomUserDetails fromUserToCustomUserDetails(User user) {
        CustomUserDetails customUserDetails = new CustomUserDetails();

        customUserDetails.id = user.getId();
        customUserDetails.email = user.getEmail();
        customUserDetails.password = user.getPassword();
        customUserDetails.grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().toString()));

        return customUserDetails;
    }
}
