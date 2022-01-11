package com.ps.petappbe.users.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String id) {
        super("User with id " + id + " has not been found.");
    }
}
