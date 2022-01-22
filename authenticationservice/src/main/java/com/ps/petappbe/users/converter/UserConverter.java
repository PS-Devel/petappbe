package com.ps.petappbe.users.converter;

import com.ps.petappbe.users.model.User;
import com.ps.petappbe.users.dto.UserDto;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserConverter {

    public static Collection<UserDto> toUserDtoCollection(Collection<User> toConvert) {
        return toConvert.stream()
                .map(UserConverter::toUserDto)
                .collect(Collectors.toList());
    }

    public static UserDto toUserDto(User toConvert) {
        return UserDto.builder()
                .username(toConvert.getUsername())
                .password(toConvert.getPassword())
                .businessAccount(toConvert.isBusinessAccount())
                .build();
    }
}
