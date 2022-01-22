package com.ps.petappbe.users.service.impl;

import com.ps.petappbe.users.dto.UserDto;

import java.util.Collection;

public interface UserService {

    UserDto findById(String id);
    Collection<UserDto> findAll();
    Collection<UserDto> findByBusinessAccount(boolean getBusinessAccount);

    UserDto createUser(UserDto userToCreate);

    UserDto updateUser(String id, UserDto userToUpdate);

    void deleteById(String id);
}
