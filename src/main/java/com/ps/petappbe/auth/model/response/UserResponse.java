package com.ps.petappbe.auth.model.response;


import lombok.Data;

import java.util.List;

@Data
public class UserResponse {
    private String id;
    private String username;
    private String email;
    private List<String> profiles;

    public UserResponse(String id, String username, String email, List<String> profiles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.profiles = profiles;
    }

}