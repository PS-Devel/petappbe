package com.ps.petappbe.auth.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Id;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private boolean businessAccount;

    public User() {

    }
    public User(String id, String username, String password,  boolean businessAccount) {
        this.id= id;
        this.username = username;
        this.password = password;
        this.businessAccount = businessAccount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBusinessAccount() {
        return businessAccount;
    }

    public void setBusinessAccount(boolean businessAccount) {
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