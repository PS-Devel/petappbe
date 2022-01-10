package com.ps.petappbe.auth.repository;

import com.ps.petappbe.auth.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

        List<User> findByUsername(String username);
        List<User> findByBusinessAccount(boolean businessAccount);
    }