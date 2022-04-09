package com.ps.user.service;

import com.ps.authentication.dto.RegistrationMessage;

public interface KafkaEmailService {

    void sendEmailTopic(RegistrationMessage registrationMessage);
}
