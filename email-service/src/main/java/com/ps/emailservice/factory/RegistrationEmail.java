package com.ps.emailservice.factory;

import com.google.common.collect.ImmutableMap;
import com.ps.emailservice.consumers.PetMessage;
import com.ps.emailservice.consumers.RegistrationMessage;
import com.ps.emailservice.dto.EmailComposeDto;

import java.util.Map;

public class RegistrationEmail implements Email {

    @Override
    public EmailComposeDto compose(PetMessage message) {
        RegistrationMessage registrationMessage = (RegistrationMessage) message;

        String toAddress = registrationMessage.getEmail();
        String fromAddress = "piessedev@gmail.com";
        String senderName = "Pet Company";
        String subject = "Confirm your PET Account";

        return EmailComposeDto.builder()
                .senderName(senderName)
                .from(fromAddress)
                .to(toAddress)
                .subject(subject)
                .build();
    }

    @Override
    public String getTemplate() {
        return "registration-email-template.html";
    }

    @Override
    public Map<String, Object> getProperties(PetMessage message) {
        RegistrationMessage registrationMessage = (RegistrationMessage) message;

        return ImmutableMap.of("name", registrationMessage.getUsername(),
                "url", registrationMessage.getActivationLink());
    }

}
