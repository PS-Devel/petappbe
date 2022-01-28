package com.ps.user.converter;

import com.ps.user.dto.CreateUserDto;
import com.ps.user.model.User;

public class UserConverter {

    public static User fromDto(CreateUserDto toConvert) {
        return User.builder()
                .username(toConvert.getUsername())
                .email(toConvert.getEmail())
                .password(toConvert.getPassword())
                .isActiveUser(toConvert.isActiveUser())
                .verificationCode(toConvert.getVerificationCode())
                .build();
    }
}
