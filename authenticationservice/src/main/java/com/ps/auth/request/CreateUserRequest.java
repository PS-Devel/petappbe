package com.ps.auth.request;

import lombok.Data;
import javax.validation.constraints.*;
import java.util.Set;

@Data
public class CreateUserRequest {

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

}