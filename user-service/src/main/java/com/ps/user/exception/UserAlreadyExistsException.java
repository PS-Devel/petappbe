package com.ps.user.exception;

import org.apache.logging.log4j.util.Strings;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(TYPE type) {
        String errorMessage;
        switch (type) {
            case EMAIL:
                errorMessage = "Email already exists.";
                break;
            case USERNAME:
                errorMessage = "Username already exists";
                break;
            default:
                errorMessage = Strings.EMPTY;
        }

        throw new RuntimeException(errorMessage);
    }

    public enum TYPE {
        EMAIL,
        USERNAME
    }
}
