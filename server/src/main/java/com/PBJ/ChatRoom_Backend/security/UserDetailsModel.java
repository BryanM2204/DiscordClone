package com.PBJ.ChatRoom_Backend.security;

import com.PBJ.ChatRoom_Backend.model.User;
import jakarta.persistence.Column;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsModel implements UserDetails {

    private final User user;

    public UserDetailsModel(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Implement logic to fetch roles/authorities for the user
        // For simplicity, let's assume all users have "ROLE_USER"
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // ... other methods (isAccountNonExpired, isAccountNonLocked, etc.)
    // Implement these based on your user entity and requirements
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
}
