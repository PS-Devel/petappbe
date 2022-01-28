package com.ps.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {

    @NotBlank
    @Size(min = 5, max = 15)
    private String username;

    @NotBlank
    @Size(max = 30)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    private Set<String> profiles;
    private String verificationCode;
    private boolean isActiveUser;

}