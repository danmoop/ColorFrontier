package com.colorfrontier.MainApp.Model;

import com.colorfrontier.MainApp.Debug;
import org.springframework.beans.factory.annotation.Autowired;
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
    private List<User> users_liked;
    private int views;

    public List<User> getUsers_liked() {
        return users_liked;
    }

    public List<Comment> getComments()
    {
        return comments;
    }

    public Project() {}

    public Project(User author, String name, String short_description, List<Comment> comments, int likes, String html, List<User> users_liked, int views)
    {
        this.name = name;
        this.author = author;
        this.short_description = short_description;
        this.comments = comments;
        this.likes = likes;
        this.html = html;
        this.users_liked = users_liked;
        this.views = views;
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

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
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

    public boolean likedProject(User user)
    {

        if(users_liked.size() == 0)
        {
            users_liked.add(user);
            setLikes(getLikes() + 1);
            return true;
        }

        else {
            int i = 0;
            for(; i < users_liked.size(); i++) {
                if(!user.getId().equals(users_liked.get(i).getId())) {
                    continue;
                } else {
                    break;
                }
            }

            if (i == users_liked.size()) {
                users_liked.add(user);
                setLikes(getLikes() + 1);

                return true;
            }
        }

        return false;
    }
    public void addView()
    {
        setViews(getViews() + 1);
    }
}
