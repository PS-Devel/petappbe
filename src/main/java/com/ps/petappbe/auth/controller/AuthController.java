package com.ps.petappbe.auth.controller;
import com.ps.petappbe.auth.model.request.LoginRequest;
import com.ps.petappbe.auth.model.request.CreateUserRequest;
import com.ps.petappbe.auth.model.response.MessageResponse;
import com.ps.petappbe.auth.model.Profile;
import com.ps.petappbe.auth.model.User;
import com.ps.petappbe.auth.model.UserProfile;
import com.ps.petappbe.auth.model.response.UserResponse;
import com.ps.petappbe.auth.repository.ProfileRepository;
import com.ps.petappbe.auth.repository.UserRepository;
import com.ps.petappbe.configuration.security.JwTokentUtils;
import com.ps.petappbe.auth.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;




@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwTokentUtils jwTokentUtils;

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
    public ResponseEntity<?> registerUser(@Valid @RequestBody CreateUserRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Username already presents!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Email already presents!"));
        }

        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        List<String> strProfile = signUpRequest.getProfiles();
        Set<Profile> profiles = new HashSet<>();

        if (strProfile == null) {
            Profile userProfile = profileRepository.findByProfile(UserProfile.PROFILE_BASIC)
                    .orElseThrow(() -> new RuntimeException("Profile not found."));
            profiles.add(userProfile);
        } else {
            strProfile.forEach(profile -> {
                switch (profile) {
                    case "business":
                        Profile businessProfile = profileRepository.findByProfile(UserProfile.PROFILE_BUSINESS)
                                .orElseThrow(() -> new RuntimeException("Profile not found."));
                        profiles.add(businessProfile);

                        break;
                    default:
                        Profile userProfile = profileRepository.findByProfile(UserProfile.PROFILE_BASIC)
                                .orElseThrow(() -> new RuntimeException("Profile not found."));
                        profiles.add(userProfile);
                }
            });
        }

        user.setProfiles(profiles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}