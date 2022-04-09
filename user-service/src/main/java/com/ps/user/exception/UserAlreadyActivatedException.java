package com.ps.user.exception;

public class UserAlreadyActivatedException extends RuntimeException {

    public UserAlreadyActivatedException(String username) {
        throw new RuntimeException("User " + username + " has been already activated. You can not activate him twice.");
    }
}
