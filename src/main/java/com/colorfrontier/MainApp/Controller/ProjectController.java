package com.colorfrontier.MainApp.Controller;


import com.colorfrontier.MainApp.Model.Project;
import com.colorfrontier.MainApp.Model.User;
import com.colorfrontier.MainApp.Service.ProjectService;
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
    ProjectService projectService;

    @PostMapping("/submitProject")
    public ModelAndView addedProject(Model model, Project project, @ModelAttribute("LoggedUser") User user)
    {
        System.out.println(project.getTitle());
        System.out.println(user.getUsername());
        System.out.println(project.getShort_description());
        System.out.println(project.getContent());

        projectService.newProjectForReview(new Project(
                project.getTitle(),
                user,
                project.getShort_description(),
                project.getContent(),
                new HashSet<>(),
                0)
        );

        return new ModelAndView("redirect:/");
    }
}
