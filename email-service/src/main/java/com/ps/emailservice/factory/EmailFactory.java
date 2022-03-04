package com.ps.emailservice.factory;

public class EmailFactory {

    public Email getEmail(EmailEnum type) {
        switch (type) {
            case REGISTRATION:
                return new RegistrationEmail();
            default:
                throw new UnsupportedOperationException("Invalid email");
        }
    }

}
