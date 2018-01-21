package com.colorfrontier.MainApp.Model;

public class Comment
{
    private User author;
    private String text;

    public Comment() {}

    public void setAuthor(User author)
    {

        this.author = author;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public User getAuthor()
    {

        return author;
    }

    public String getText()
    {
        return text;
    }

    public Comment(User author, String text)
    {

        this.author = author;
        this.text = text;
    }
}
