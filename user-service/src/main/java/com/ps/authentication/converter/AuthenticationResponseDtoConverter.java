package com.ps.authentication.converter;

import com.ps.authentication.dto.AuthenticationResponseDto;

import java.util.Collection;

public class AuthenticationResponseDtoConverter {

    public static AuthenticationResponseDto toDto(String id, String email, String username, Collection<String> profiles) {
        return AuthenticationResponseDto.builder()
                .id(id)
                .email(email)
                .username(username)
                .profiles(profiles)
                .build();
    }
}
