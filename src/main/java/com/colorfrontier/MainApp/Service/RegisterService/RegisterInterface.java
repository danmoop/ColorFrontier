package com.colorfrontier.MainApp.Service.RegisterService;

import com.colorfrontier.MainApp.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RegisterInterface extends MongoRepository<User, String>{

}
