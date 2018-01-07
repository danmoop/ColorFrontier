package com.colorfrontier.MainApp.Model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "users")
public class User
{
    private String username;
    private String email;
    private String password;
    private Boolean banned;

    private Set<Project> projects;

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

    public User(String username, String email, String password, Boolean banned, Set<Project> projects) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.banned = banned;
        this.projects = projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public Set<Project> getProjects() {

        return projects;
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
