package com.colorfrontier.MainApp.Service;

import com.colorfrontier.MainApp.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RegisterInterface extends MongoRepository<User, String>{
    public User findByUsername(String username);
    public User findByEmail(String email);
}
