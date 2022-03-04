package com.ps.emailservice.factory;

import com.ps.emailservice.consumers.PetMessage;
import com.ps.emailservice.consumers.RegistrationMessage;
import com.ps.emailservice.dto.EmailComposeDto;

public class RegistrationEmail implements Email {

    @Override
    public EmailComposeDto compose(PetMessage message) {
        RegistrationMessage registrationMessage = (RegistrationMessage) message;

        String toAddress = registrationMessage.getEmail();
        String fromAddress = "piessedev@gmail.com";
        String senderName = "PS-DEV";
        String subject = "Pet App New User";
        String content = "Dear [[name]],<br>"
                + "Click to confirm your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">CONFIRM</a></h3>"
                + "Thank you,<br>"
                + "PS-DEV";

        content = content.replace("[[name]]", registrationMessage.getUsername());
        String URL = registrationMessage.getActivationLink();
        content = content.replace("[[URL]]", URL);

        return EmailComposeDto.builder()
                .senderName(senderName)
                .from(fromAddress)
                .to(toAddress)
                .subject(subject)
                .content(content)
                .build();
    }

}
