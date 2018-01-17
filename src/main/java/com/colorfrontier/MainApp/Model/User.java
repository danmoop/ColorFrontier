package com.colorfrontier.MainApp.Model;


import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Document(collection = "users")
public class User
{
    private String username;
    private String email;
    private String password;
    private Boolean banned;
    private Set<Project> projects;
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    public String getUsername() {

        return username;
    }

    public User(String username, String email, String password, Boolean banned, Set<Project> projects, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.banned = banned;
        this.projects = projects;
        this.role = role;
    }

    public Set<Project> getProjects() {

        return projects;
    }

    public void addProject(Project project)
    {
        projects.add(project);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getBanned() {
        return banned;
    }

    public User() {}
}
