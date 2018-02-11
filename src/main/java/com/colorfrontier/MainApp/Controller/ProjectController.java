package com.colorfrontier.MainApp.Controller;


import com.colorfrontier.MainApp.Debug;
import com.colorfrontier.MainApp.Model.Comment;
import com.colorfrontier.MainApp.Model.Project;
import com.colorfrontier.MainApp.Model.User;
import com.colorfrontier.MainApp.Service.ProjectInterface;
import com.colorfrontier.MainApp.Service.RegisterInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.unbescape.html.HtmlEscape;

import java.io.*;
import java.util.ArrayList;

import static org.thymeleaf.util.StringUtils.unescapeJava;

@Controller
@SessionAttributes(value = "LoggedUser")
public class ProjectController
{
    @Autowired
    ProjectInterface projectInterface;

    @Autowired
    RegisterInterface registerInterface;

    @GetMapping("/{title}")
    public ModelAndView projectPage(@PathVariable String title, Model model)
    {
        model.addAttribute("Project", projectInterface.findByName(title));
        model.addAttribute("ContentHTML", unescapeJava(projectInterface.findByName(title).getHtml()));
        model.addAttribute("CommentObject", new Comment());

        Project projectFromDB = projectInterface.findByName(title);

        projectFromDB.addView();
        projectFromDB.getAuthor().addView();

        projectInterface.save(projectFromDB);

        User userFromDB = registerInterface.findByUsername(projectFromDB.getAuthor().getUsername());

        userFromDB.addView();

        registerInterface.save(userFromDB);

        return new ModelAndView("sections/projectPage");
    }

    @PostMapping("/deleteProject")
    public ModelAndView deleteProject(@ModelAttribute Project project, Model model, @ModelAttribute("LoggedUser") User user)
    {
        Project ProjectFromDB = projectInterface.findByName(project.getName());

        projectInterface.delete(ProjectFromDB);

        User userFromDB = registerInterface.findByUsername(user.getUsername());

        userFromDB.deleteProject(ProjectFromDB);

        registerInterface.save(userFromDB);

        return new ModelAndView("redirect:/dashboard");


    }

    @PostMapping("/publishProject")
    public ModelAndView publishProject(@ModelAttribute Project project, @RequestBody String projectdata, @ModelAttribute("LoggedUser") User user) throws UnsupportedEncodingException, FileNotFoundException {

        System.out.println(project.getHtml());

        InputStream inputStream = null;

        Project userProject = new Project(
                user,
                project.getName(),
                project.getShort_description(),
                new ArrayList<Comment>(),
                0,
                HtmlEscape.unescapeHtml(project.getHtml()),
                new ArrayList<User>(),
                0

        );

        projectInterface.save(userProject);

        User user1 = registerInterface.findByUsername(user.getUsername());

        user1.addProject(userProject);

        registerInterface.save(user1);


        return new ModelAndView("redirect:/dashboard");
    }

    @PostMapping("/likeProject")
    public ModelAndView likeProject(Model model, @ModelAttribute Project project, @ModelAttribute("LoggedUser") User user)
    {
        Project projectFromDB = projectInterface.findByName(project.getName());

        if(projectFromDB.likedProject(user))
        {
            User userFromDB = registerInterface.findByUsername(project.getAuthor().getUsername());

            userFromDB.addLike();

            registerInterface.save(userFromDB);

        }

        projectInterface.save(projectFromDB);

        return new ModelAndView("redirect:/" + project.getName());
    }

    @PostMapping("/sendComment")
    public ModelAndView sendComment(Model model, @ModelAttribute Comment comment, @ModelAttribute("LoggedUser") User user)
    {
       /* Comment comment1 = new Comment(
                user,
                comment.getText(),
                comment.getFromWhichProject()
        );

        Project projectFromDB = projectInterface.findByName(comment.getFromWhichProject());

        projectFromDB.addComment(comment1);

        projectInterface.save(projectFromDB);*/

       Debug.Log(user.getUsername());
       Debug.Log(comment.getText());
       Debug.Log(comment.getFromWhichProject());

       if(!user.getUsername().equals("null"))
       {
           Comment comment1 = new Comment(
                   user,
                   comment.getText(),
                   comment.getFromWhichProject()
           );

           Project projectFromDB = projectInterface.findByName(comment.getFromWhichProject());

           projectFromDB.addComment(comment1);

           projectInterface.save(projectFromDB);
       }

       return new ModelAndView("redirect:/" + comment.getFromWhichProject());
    }
}