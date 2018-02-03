package com.projecthideout.MainApplication.Service;

import com.projecthideout.MainApplication.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RegisterInterface extends MongoRepository<User, String> {
    User findByUsername(String username);
}
