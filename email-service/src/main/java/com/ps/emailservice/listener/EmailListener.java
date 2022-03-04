package com.ps.emailservice.listener;

import com.ps.emailservice.consumers.RegistrationMessage;
import com.ps.emailservice.service.EmailService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

import static com.ps.emailservice.config.ConsumerGroupId.AUTHEMAILGROUP;
import static com.ps.emailservice.config.ConsumerTopic.AUTHEMAILTOPIC;
import static com.ps.emailservice.factory.EmailEnum.REGISTRATION;


@Slf4j
@Component
public class EmailListener {

    @Setter(onMethod = @__({@Autowired}))
    private EmailService emailService;

    @KafkaListener(topics = AUTHEMAILTOPIC, groupId = AUTHEMAILGROUP, containerFactory = "registrationFactory")
    void registrationListener(RegistrationMessage registrationMessage) throws MessagingException, UnsupportedEncodingException {
        log.trace("TOPIC: auth-email-topic - received message {}", registrationMessage.toString());

        emailService.sendEmail(REGISTRATION, registrationMessage);
    }

}
