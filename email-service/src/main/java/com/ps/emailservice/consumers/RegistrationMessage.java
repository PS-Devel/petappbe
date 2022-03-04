package com.ps.emailservice.consumers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationMessage implements PetMessage {

    private String username;
    private String activationLink;
    private String email;

}