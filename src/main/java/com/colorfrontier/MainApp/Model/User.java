package com.colorfrontier.MainApp.Model;


import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Document(collection = "users")
public class User
{
    @NotEmpty
    @NotNull
    private String username;


    @NotEmpty
    @NotNull
    private String email;


    @NotEmpty
    @NotNull
    private String password;


    @NotEmpty
    @NotNull
    private Boolean banned;


    @NotEmpty
    @NotNull
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
