package com.ps.authentication.dto;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class AuthenticationDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}