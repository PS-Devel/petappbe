package com.ps.petappbe.users.controller;

import com.ps.petappbe.users.dto.UserDto;
import com.ps.petappbe.users.service.impl.UserService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/users")
public class UserController {

    @Setter(onMethod = @__({@Autowired}))
    private UserService userService;

    @GetMapping
    public Collection<UserDto> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/by-id/{id}")
    public UserDto getUserById(@PathVariable String id) {
        return userService.findById(id);
    }

    @GetMapping("/business")
    public Collection<UserDto> findByBusinessAccount() {
        return userService.findByBusinessAccount(true);
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userToCreate) {
        return userService.createUser(userToCreate);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable String id, @RequestBody UserDto userToUpdate) {
        return userService.updateUser(id, userToUpdate);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteById(id);
    }
}