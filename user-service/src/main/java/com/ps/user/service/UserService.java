package com.ps.user.service;

import com.ps.user.dto.CreateUserDto;
import com.ps.user.model.User;

public interface UserService {
    User createUser(CreateUserDto toCreate);

    void setVerificationCode(User user);

    void activateUser(String token);

    User reinitializeActivationToken(String userId);
}
