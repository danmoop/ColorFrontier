package com.colorfrontier.MainApp.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "projects")
public class Project
{
    private String title;
    private User author;
    private String short_description;
    private String content;
    private Set<Comment> comments;
    private int likes;

    public void setContent(String content)
    {
        this.content = content;
    }

    public Set<Comment> getComments()
    {
        return comments;
    }

    public void addComment(User author, String text)
    {
        comments.add(new Comment(author, text));
    }

    public Project() {}

    public String getContent()
    {
        return content;
    }

    public int getLikes()
    {
        return likes;
    }

    public Project(String title, User author, String short_description, String content, Set<Comment> comments, int likes)
    {
        this.title = title;
        this.author = author;
        this.short_description = short_description;
        this.content = content;
        this.comments = comments;
        this.likes = likes;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setAuthor(User author)
    {
        this.author = author;
    }

    public void setShort_description(String short_description)
    {
        this.short_description = short_description;
    }

    public String getTitle()
    {

        return title;
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
