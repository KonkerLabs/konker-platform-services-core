package com.konkerlabs.platform.registry.core.common.repositories;

import com.konkerlabs.platform.registry.core.common.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PasswordBlacklistRepository
        extends MongoRepository<User.PasswordBlacklist, String> {

    @Query("{ 'text': 0 }")
    List<User.PasswordBlacklist> findMatches(String text);
}
