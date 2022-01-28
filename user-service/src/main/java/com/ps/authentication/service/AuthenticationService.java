package com.ps.authentication.service;

import com.ps.authentication.dto.AuthenticationDto;

public interface AuthenticationService {
    void authenticateMe(AuthenticationDto authenticationDto);
}
