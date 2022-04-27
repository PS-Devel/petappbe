package com.ps.user.converter;

import com.ps.user.dto.CreateUserDto;
import com.ps.user.model.Profile;
import com.ps.user.model.User;

import java.util.stream.Collectors;

public class UserConverter {

    public static User fromDto(CreateUserDto toConvert) {
        return User.builder()
                .username(toConvert.getUsername())
                .email(toConvert.getEmail())
                .password(toConvert.getPassword())
                .profiles(toConvert.getProfiles().stream()
                        .map(v -> Profile.builder().profile(v).build())
                        .collect(Collectors.toSet()))
                .build();
    }
}
