package com.ps.petappbe.auth.model.request;



import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;

@Data
public class CreateUserRequest {
    @NotBlank
    @Size(min = 5, max = 15)
    private String username;

    @NotBlank
    @Size(max = 30)
    @Email
    private String email;

    private List<String> profiles;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

}