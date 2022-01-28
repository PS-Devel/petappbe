package com.ps.authentication.currentuser.impl;

import com.ps.authentication.currentuser.CurrentUser;
import com.ps.authentication.model.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class CurrentUserImpl implements CurrentUser {

    @Override
    public CustomUserDetails getCustomUserDetails() {
        return (CustomUserDetails) this.getAuthentication().getPrincipal();
    }

    @Override
    public String getId() {
        return this.getCustomUserDetails().getId();
    }

    @Override
    public String getUsername() {
        return this.getCustomUserDetails().getUsername();
    }

    @Override
    public String getEmail() {
        return this.getCustomUserDetails().getEmail();
    }

    @Override
    public String getPassword() {
        return this.getCustomUserDetails().getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Collection<String> getStringAuthorities() {
        return this.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
