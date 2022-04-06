package com.ps.emailservice.service.impl;

import com.ps.emailservice.consumers.PetMessage;
import com.ps.emailservice.dto.EmailComposeDto;
import com.ps.emailservice.factory.Email;
import com.ps.emailservice.factory.EmailEnum;
import com.ps.emailservice.factory.EmailFactory;
import com.ps.emailservice.service.EmailService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class EmailServiceImpl implements EmailService {

    @Setter(onMethod = @__({@Autowired}))
    private SpringTemplateEngine springTemplateEngine;

    @Setter(onMethod = @__({@Autowired}))
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(EmailEnum type, PetMessage petMessage) throws MessagingException, UnsupportedEncodingException {
        EmailFactory emailFactory = new EmailFactory();
        Email email = emailFactory.getEmail(type);

        EmailComposeDto composeDto = email.compose(petMessage);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(composeDto.getFrom(), composeDto.getSenderName());
        helper.setTo(composeDto.getTo());
        helper.setSubject(composeDto.getSubject());

        Context context = new Context();
        context.setVariables(email.getProperties(petMessage));
        String html = springTemplateEngine.process(email.getTemplate(), context);
        helper.setText(html, true);

        mailSender.send(message);
    }
}