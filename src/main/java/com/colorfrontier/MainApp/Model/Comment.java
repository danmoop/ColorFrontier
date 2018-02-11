package com.colorfrontier.MainApp.Model;

public class Comment
{
    private User author;
    private String text;
    private String fromWhichProject;

    public void setFromWhichProject(String fromWhichProject) {
        this.fromWhichProject = fromWhichProject;
    }

    public String getFromWhichProject() {

        return fromWhichProject;
    }

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

    public Comment(User author, String text, String fromWhichProject) {
        this.author = author;
        this.text = text;
        this.fromWhichProject = fromWhichProject;
    }
}
