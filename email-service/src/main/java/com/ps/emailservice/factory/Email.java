package com.ps.emailservice.factory;

import com.ps.emailservice.consumers.PetMessage;
import com.ps.emailservice.dto.EmailComposeDto;

public interface Email {

    EmailComposeDto compose(PetMessage message);
}
