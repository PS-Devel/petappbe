package com.ps.user.service.impl;

import com.ps.user.dto.CreateUserDto;
import com.ps.user.exception.UserAlreadyActivatedException;
import com.ps.user.exception.UserAlreadyExistsException;
import com.ps.user.exception.UserNotFoundException;
import com.ps.user.model.User;
import com.ps.user.repository.UserRepository;
import com.ps.user.service.UserService;
import lombok.Setter;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.ps.user.converter.UserConverter.fromDto;
import static com.ps.user.exception.UserAlreadyExistsException.TYPE.EMAIL;
import static com.ps.user.exception.UserAlreadyExistsException.TYPE.USERNAME;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Setter(onMethod = @__({@Autowired}))
    private PasswordEncoder passwordEncoder;

    @Setter(onMethod = @__({@Autowired}))
    private UserRepository userRepository;

    @Override
    public User createUser(CreateUserDto toCreate) {
        checkUser(toCreate);

        User user = fromDto(toCreate);

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setActiveUser(false);

        setVerificationCode(user);

        return userRepository.save(user);
    }

    @Override
    public void setVerificationCode(User user) {
        if (user.isActiveUser()) {
            throw new UserAlreadyActivatedException(user.getUsername());
        }

        String randomCode = RandomString.make(64);
        user.setVerificationCode(randomCode);
    }

    @Override
    public void activateUser(String token) {
        Optional<User> byVerificationCodeOptional = userRepository.findByVerificationCode(token);

        User user = byVerificationCodeOptional.orElseThrow(() -> new UserNotFoundException("User not exists or already been accepted"));

        user.setActiveUser(true);
        user.setVerificationCode(null);

        userRepository.save(user);
    }

    @Override
    public User reinitializeActivationToken(String userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        User user = userOptional.get();

        setVerificationCode(user);

        userRepository.save(user);

        return user;
    }

    private void checkUser(CreateUserDto toCreate) {
        Boolean existsByUsername = userRepository.existsByUsername(toCreate.getUsername());
        if (existsByUsername) {
            throw new UserAlreadyExistsException(USERNAME);
        }

        Boolean existsByEmail = userRepository.existsByEmail(toCreate.getEmail());
        if (existsByEmail) {
            throw new UserAlreadyExistsException(EMAIL);
        }
    }
}
