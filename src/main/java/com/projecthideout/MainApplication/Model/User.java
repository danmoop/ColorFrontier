package com.projecthideout.MainApplication.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
public class User
{
    @Id
    private String id;
    private String username;
    private String email;
    private String password;
    private List<Project> projects;
    private boolean banned;
    private int totallikes;
    private int totalviews;

    public User(){}

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public int getTotallikes() {
        return totallikes;
    }

    public void setTotallikes(int totallikes) {
        this.totallikes = totallikes;
    }

    public int getTotalviews() {
        return totalviews;
    }

    public void setTotalviews(int totalviews) {
        this.totalviews = totalviews;
    }

    public User(String username, String email, String password, List<Project> projects, boolean banned, int totallikes, int totalviews)
    {
        this.username = username;
        this.email = email;
        this.password = password;
        this.banned = banned;
        this.projects = projects;
        this.totallikes = totallikes;
        this.totalviews = totalviews;

    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public boolean isBanned()
    {
        return banned;
    }

    public void setBanned(boolean banned)
    {
        this.banned = banned;
    }

    public void addProject(Project project)
    {
        projects.add(project);
    }
}
