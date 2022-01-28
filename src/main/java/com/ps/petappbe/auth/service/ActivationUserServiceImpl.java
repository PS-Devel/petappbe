package com.ps.petappbe.auth.service;


import com.ps.petappbe.auth.model.User;
import com.ps.petappbe.auth.repository.UserRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class ActivationUserServiceImpl {

    @Autowired
    private UserRepository repo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JavaMailSender mailSender;


    public void register(User user, String siteURL) throws UnsupportedEncodingException, MessagingException {

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        String randomCode = RandomString.make(64);
        user.setVerificationCode(randomCode);
        user.setActiveUser(false);
        repo.save(user);

        sendVerificationEmail(user, siteURL);
    }


    private void sendVerificationEmail(User user, String siteURL)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String fromAddress = "piessedev@gmail.com";
        String senderName = "PS-DEV";
        String subject = "Pet App New User";
        String content = "Dear [[name]],<br>"
                + "Click to confirm your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">CONFIRM</a></h3>"
                + "Thank you,<br>"
                + "PS-DEV";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        content = content.replace("[[name]]", user.getUsername());
        String URL = siteURL + "/api/auth/verify?verificationCode=" + user.getVerificationCode();
        content = content.replace("[[URL]]", URL);
        helper.setText(content, true);

        mailSender.send(message);

    }


    public boolean verify(String verificationCode) {
        User user = repo.findByVerificationCode(verificationCode);

        if (user == null || user.isActiveUser()) {

            return false;
        } else {
            user.setVerificationCode(null);
            user.setActiveUser(true);
            repo.save(user);

            return true;
        }

    }

}
