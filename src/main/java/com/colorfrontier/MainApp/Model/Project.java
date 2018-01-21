package com.colorfrontier.MainApp.Model;

import java.util.Set;

public class Project
{
    private String title;
    private User author;
    private String short_description;
    private Set<Comment> comments;
    private int likes;

    public Set<Comment> getComments()
    {
        return comments;
    }

    public void addComment(User author, String text)
    {
        comments.add(new Comment(author, text));
    }

    public Project() {}

    public Project(String title, User author, String short_description, Set<Comment> comments)
    {
        this.title = title;
        this.author = author;
        this.short_description = short_description;
        this.comments = comments;
        this.likes = 0;
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
