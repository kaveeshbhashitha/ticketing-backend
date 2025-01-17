package com.ticketing.ticketing_backend.Config;

import com.ticketing.ticketing_backend.Model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserInfoUserDetails implements UserDetails {

    private String email; // Updated to match `userEmail`
    private String password;
    private List<GrantedAuthority> authorities;

    // Constructor to initialize user details
    public UserInfoUserDetails(User user) {
        this.email = user.getUserEmail(); // Set email from `User` model
        this.password = user.getPassword(); // Set password from `User` model
        // Assign role with prefix `ROLE_`
        this.authorities = List.of(new SimpleGrantedAuthority("ROLE_" + user.getUserRole().trim()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email; // Email is used as the username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Customize if needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Customize if needed
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Customize if needed
    }

    @Override
    public boolean isEnabled() {
        return true; // Customize if needed
    }
}

