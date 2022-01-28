package com.ps.authentication.service.impl;

import com.ps.authentication.dto.AuthenticationDto;
import com.ps.authentication.service.AuthenticationService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Setter(onMethod = @__({@Autowired}))
    private AuthenticationManager authenticationManager;

    @Override
    public void authenticateMe(AuthenticationDto authenticationDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authenticationDto.getUsername(), authenticationDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
