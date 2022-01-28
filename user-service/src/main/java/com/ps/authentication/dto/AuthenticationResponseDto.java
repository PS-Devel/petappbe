package com.ps.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponseDto {
    private String id;
    private String username;
    private String email;
    private Collection<String> profiles;
}
