package com.ps.emailservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailComposeDto {

    private String senderName;
    private String from;
    private String to;
    private String content;
    private String subject;
}
