package com.colorfrontier.MainApp.Controller;


import com.colorfrontier.MainApp.Model.Project;
import com.colorfrontier.MainApp.Model.User;
import com.colorfrontier.MainApp.Service.ProjectInterface;
import com.colorfrontier.MainApp.Service.RegisterService.RegisterInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;

@Controller
@SessionAttributes(value = "LoggedUser")
public class ProjectController
{
    @Autowired
    ProjectInterface projectInterface;

    @Autowired
    RegisterInterface registerInterface;

    @PostMapping("/submitProject")
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

        return new ModelAndView("redirect:/");
    }
}