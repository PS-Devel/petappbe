package com.ps.emailservice.factory;

import com.ps.emailservice.consumers.PetMessage;
import com.ps.emailservice.dto.EmailComposeDto;

import java.util.Map;

public interface Email {

    EmailComposeDto compose(PetMessage message);

    String getTemplate();

    Map<String, Object> getProperties(PetMessage message);
}
