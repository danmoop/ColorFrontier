package com.colorfrontier.MainApp.Controller;


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

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import static org.thymeleaf.util.StringUtils.unescapeJava;
import static org.unbescape.html.HtmlEscape.unescapeHtml;

@Controller
@SessionAttributes(value = "LoggedUser")
public class ProjectController
{
    @Autowired
    ProjectInterface projectInterface;

    @Autowired
    RegisterInterface registerInterface;

    /*@PostMapping("/submitProject")
    public ModelAndView addedProject(Model model, Project project, @ModelAttribute("LoggedUser") User user)
    {
        Project userProject = new Project(
                project.getTitle(),
                user,
                project.getShort_description(),
                project.getContent(),
                project.getComments(),
                0);

        projectInterface.save(userProject);

        User user1 = registerInterface.findByUsername(user.getUsername());

        user1.addProject(userProject);

        registerInterface.save(user1);

        return new ModelAndView("redirect:/dashboard");
    }*/

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

    @GetMapping("/{title}")
    public ModelAndView projectPage(@PathVariable String title, Model model)
    {
        model.addAttribute("Project", projectInterface.findByName(title));
        model.addAttribute("ContentHTML", unescapeJava(projectInterface.findByName(title).getHtml()));

        return new ModelAndView("sections/projectPage");
    }

    @PostMapping("/publishProject")
    public ModelAndView publishProject(@ModelAttribute Project project, @RequestBody String projectdata, @ModelAttribute("LoggedUser") User user) throws UnsupportedEncodingException {

        System.out.println(project.getHtml());

        Project userProject = new Project(
                user,
                project.getName(),
                project.getShort_description(),
                new ArrayList<Comment>(),
                0,
                HtmlEscape.unescapeHtml(project.getHtml()),
                new ArrayList<User>()
        );

        projectInterface.save(userProject);

        User user1 = registerInterface.findByUsername(user.getUsername());

        user1.addProject(userProject);

        registerInterface.save(user1);


        return new ModelAndView("redirect:/dashboard");
    }
}