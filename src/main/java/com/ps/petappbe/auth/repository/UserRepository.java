package com.ps.petappbe.auth.repository;

import com.ps.petappbe.auth.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    User findByVerificationCode(String verificationCode);



}
