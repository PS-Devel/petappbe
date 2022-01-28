package com.ps.user.service;

import com.ps.user.dto.CreateUserDto;
import com.ps.user.model.User;

public interface UserService {
    User createUser(CreateUserDto toCreate);

    String generateToken(String baseUrl, User user);

    void activateUser(String token);
}
