package com.ps.auth.controller;


import com.ps.auth.configuration.security.JwTokenUtils;
import com.ps.auth.model.User;
import com.ps.auth.request.LoginRequest;
import com.ps.auth.response.UserResponse;
import com.ps.auth.repository.ProfileRepository;
import com.ps.auth.repository.UserRepository;
import com.ps.auth.service.ActivationUserServiceImpl;
import com.ps.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    ActivationUserServiceImpl activationService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    JwTokenUtils jwTokentUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserService userDetails = (UserService) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwTokentUtils.generateJwtCookie(userDetails);

        List<String> profiles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        profiles));
    }

    @PostMapping("/createUser")
    public String create(User user, HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {

        if (userRepository.existsByUsername(user.getUsername())) {

            return "User already exists";

        } else if (userRepository.existsByEmail(user.getEmail())) {

            return "Email already exists";

        } else {

            activationService.register(user, getSiteURL(request));

            return "User created successfully!";
        }
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    @GetMapping("/verify")
    public String verifyUser(@Param("verificationCode") String verificationCode) {

        if (activationService.verify(verificationCode)) {

            return "active";
        } else {
            return "Inactive";
        }
    }

}