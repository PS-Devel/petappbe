package com.ps.petappbe.users.service.impl;

import com.ps.petappbe.users.model.User;
import com.ps.petappbe.users.repository.UserRepository;
import com.ps.petappbe.users.dto.UserDto;
import com.ps.petappbe.users.exception.UserNotFoundException;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static com.ps.petappbe.users.converter.UserConverter.toUserDto;
import static com.ps.petappbe.users.converter.UserConverter.toUserDtoCollection;

@Service
public class UserServiceImpl implements UserService {

    @Setter(onMethod = @__({@Autowired}))
    private UserRepository userRepository;

    @Override
    public UserDto findById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return toUserDto(user);
    }

    @Override
    public Collection<UserDto> findAll() {
        Collection<User> allUsers = userRepository.findAll();

        return toUserDtoCollection(allUsers);
    }

    @Override
    public Collection<UserDto> findByBusinessAccount(boolean getBusinessAccount) {
        Collection<User> allUsersByBusiness = userRepository.findByBusinessAccount(true);

        return toUserDtoCollection(allUsersByBusiness);
    }

    @Override
    public UserDto createUser(UserDto userToCreate) {
        User createdUser = userRepository.save(new User(null, userToCreate.getUsername(), userToCreate.getPassword(), userToCreate.isBusinessAccount()));

        return toUserDto(createdUser);
    }

    @Override
    public UserDto updateUser(String id, UserDto userToUpdate) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        user.setUsername(userToUpdate.getUsername());
        user.setPassword(userToUpdate.getPassword());
        user.setBusinessAccount(userToUpdate.isBusinessAccount());

        return toUserDto(user);
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
}
