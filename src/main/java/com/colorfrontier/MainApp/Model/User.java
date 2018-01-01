package com.colorfrontier.MainApp.Model;


import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user_data")
public class User
{
    private String username;
    private String email;
    private String password;
    private Boolean banned;

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

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getBanned() {
        return banned;
    }

    public User(String username, String email, String password, Boolean banned) {

        this.username = username;
        this.email = email;
        this.password = password;
        this.banned = banned;
    }

    public User() {}
}
