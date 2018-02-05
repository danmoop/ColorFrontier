package com.colorfrontier.MainApp.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "projects")
public class Project
{
    @Id
    private String id;
    private String name;
    private User author;
    private String short_description;
    private List<Comment> comments;
    private int likes;
    private String html;


    public List<Comment> getComments()
    {
        return comments;
    }

    public Project() {}

    public Project(User author, String name, String short_description, List<Comment> comments, int likes, String html)
    {
        this.name = name;
        this.author = author;
        this.short_description = short_description;
        this.comments = comments;
        this.likes = likes;
        this.html = html;
    }

    public int getLikes()
    {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(User author)
    {
        this.author = author;
    }

    public void setShort_description(String short_description)
    {
        this.short_description = short_description;
    }

    public User getAuthor()
    {
        return author;
    }

    public String getShort_description()
    {
        return short_description;
    }
}
