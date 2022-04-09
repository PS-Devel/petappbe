package com.ps.controller;


import com.ps.authentication.converter.AuthenticationResponseDtoConverter;
import com.ps.authentication.currentuser.CurrentUser;
import com.ps.authentication.dto.AuthenticationDto;
import com.ps.authentication.dto.AuthenticationResponseDto;
import com.ps.authentication.dto.RegistrationMessage;
import com.ps.authentication.service.AuthenticationService;
import com.ps.authentication.utils.JwTokenUtils;
import com.ps.user.dto.CreateUserDto;
import com.ps.user.model.User;
import com.ps.user.service.ActivationLinkService;
import com.ps.user.service.KafkaEmailService;
import com.ps.user.service.UserService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.ps.utils.BaseUrlUtils.getBasicUrlFromRequest;
import static org.springframework.http.HttpHeaders.SET_COOKIE;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Setter(onMethod = @__({@Autowired}))
    private CurrentUser currentUser;

    @Setter(onMethod = @__({@Autowired}))
    private AuthenticationService authenticationService;
    @Setter(onMethod = @__({@Autowired}))
    private KafkaEmailService kafkaEmailService;
    @Setter(onMethod = @__({@Autowired}))
    private ActivationLinkService activationLinkService;
    @Setter(onMethod = @__({@Autowired}))
    private UserService userService;

    @Setter(onMethod = @__({@Autowired}))
    private JwTokenUtils jwTokentUtils;


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> authenticateUser(@Valid @RequestBody AuthenticationDto authenticationDto) {
        authenticationService.authenticateMe(authenticationDto);

        ResponseCookie jwtCookie = jwTokentUtils.generateJwtCookie(currentUser.getCustomUserDetails());

        return ResponseEntity.ok().header(SET_COOKIE, jwtCookie.toString())
                .body(AuthenticationResponseDtoConverter.toDto(currentUser.getId(), currentUser.getEmail(), currentUser.getUsername(), currentUser.getStringAuthorities()));
    }

    @PostMapping("/create")
    public void create(CreateUserDto toCreate, HttpServletRequest request) {
        User createdUser = userService.createUser(toCreate);

        String activationLink = activationLinkService.getActivationLink(getBasicUrlFromRequest(request), createdUser.getVerificationCode());
        kafkaEmailService.sendEmailTopic(RegistrationMessage.builder().username(createdUser.getUsername()).email(createdUser.getEmail()).activationLink(activationLink).build());
    }

    @PostMapping("/resend-activation/user/{userId}")
    public void resendActivation(@PathVariable String userId, HttpServletRequest request) {
        User user = userService.reinitializeActivationToken(userId);

        String activationLink = activationLinkService.getActivationLink(getBasicUrlFromRequest(request), user.getVerificationCode());
        kafkaEmailService.sendEmailTopic(RegistrationMessage.builder().username(user.getUsername()).email(user.getEmail()).activationLink(activationLink).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping("/activate/token/{token}")
    private void activateUser(@PathVariable String token) {
        userService.activateUser(token);
    }

}