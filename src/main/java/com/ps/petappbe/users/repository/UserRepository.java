package com.ps.petappbe.users.repository;

import com.ps.petappbe.users.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

        List<User> findByUsername(String username);
        List<User> findByBusinessAccount(boolean businessAccount);
    }