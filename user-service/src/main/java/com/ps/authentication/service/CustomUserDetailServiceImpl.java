package com.ps.authentication.service;

import com.ps.authentication.model.CustomUserDetails;
import com.ps.user.model.User;
import com.ps.user.repository.UserRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CustomUserDetailServiceImpl implements UserDetailsService {

    @Setter(onMethod = @__({@Autowired}))
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);

        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));

        return CustomUserDetails.build(user);
    }
}
