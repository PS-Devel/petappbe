package com.ps.auth.repository;

import com.ps.auth.model.Profile;
import com.ps.auth.model.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProfileRepository extends MongoRepository<Profile, String> {

    Optional<Profile> findByProfile(UserProfile profile);

}