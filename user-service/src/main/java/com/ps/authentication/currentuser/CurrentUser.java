package com.ps.authentication.currentuser;

import com.ps.authentication.model.CustomUserDetails;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface CurrentUser {

    CustomUserDetails getCustomUserDetails();

    String getId();

    String getUsername();

    String getEmail();

    String getPassword();

    Collection<? extends GrantedAuthority> getAuthorities();

    Collection<String> getStringAuthorities();

}
