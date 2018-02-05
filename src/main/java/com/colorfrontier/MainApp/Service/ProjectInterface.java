package com.colorfrontier.MainApp.Service;

import com.colorfrontier.MainApp.Model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectInterface extends MongoRepository<Project, String>
{
    public Project findByName(String name);
}