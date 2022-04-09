package com.ps.user.service.impl;

import com.ps.authentication.dto.RegistrationMessage;
import com.ps.user.service.KafkaEmailService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaEmailServiceImpl implements KafkaEmailService {

    @Setter(onMethod = @__({@Autowired}))
    private KafkaTemplate<String, RegistrationMessage> kafkaTemplate;

    @Override
    public void sendEmailTopic(RegistrationMessage registrationMessage) {
        kafkaTemplate.send("auth-email-topic", registrationMessage);
    }
}
