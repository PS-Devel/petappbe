package com.ps.petappbe.auth.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Id;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private boolean businessAccount;

    public User() {

    }
    public User(String username, String password, boolean published) {
        this.username = username;
        this.password = password;
        this.businessAccount = businessAccount;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", businessAccount=" + businessAccount +
                '}';
    }
}