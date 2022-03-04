package com.ps.emailservice.service;

import com.ps.emailservice.consumers.PetMessage;
import com.ps.emailservice.factory.EmailEnum;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface EmailService {
    void sendEmail(EmailEnum type, PetMessage message) throws MessagingException, UnsupportedEncodingException;
}
