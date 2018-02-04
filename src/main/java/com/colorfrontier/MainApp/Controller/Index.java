package com.colorfrontier.MainApp.Controller;

import com.colorfrontier.MainApp.Model.Project;
import com.colorfrontier.MainApp.Model.User;
import com.colorfrontier.MainApp.Service.ProjectInterface;
import com.colorfrontier.MainApp.Service.RegisterInterface;
import com.colorfrontier.MainApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;

@Controller
@SessionAttributes(value = "LoggedUser")

public class Index
{

    @Autowired
    RegisterInterface registerInterface;

    @Autowired
    ProjectInterface projectInterface;

    @Autowired
    UserService userService;

    public Index() throws NoSuchAlgorithmException {
    }

    @GetMapping("/dashboard")
    public ModelAndView dashboard(Model model, @ModelAttribute(value = "LoggedUser") User user, HttpServletRequest request)
    {
        ModelAndView dashboard = new ModelAndView();
        model.addAttribute("LoggedUser", registerInterface.findByUsername(user.getUsername()));
        model.addAttribute("UserProjects", registerInterface.findByUsername(user.getUsername()).getProjects());

        if(user.getUsername() == null)
        {
            dashboard.setViewName("redirect:/");
            return dashboard;
        }

        else
        {
            dashboard.setViewName("sections/dashboard");
            return dashboard;
        }
    }

    @GetMapping("/")
    public ModelAndView index(Model model, @ModelAttribute(value = "LoggedUser") User user, HttpServletRequest request)
    {
        model.addAttribute("LoggedUser", user);
        model.addAttribute("Projects", projectInterface.findAll());

        User loggedUser = (User) request.getSession().getAttribute("LoggedUser");

        ModelAndView indexPage = new ModelAndView();

        indexPage.setViewName("sections/index");

        return indexPage;
    }

    @ModelAttribute(value = "LoggedUser")
    public User nullUser() {
        return new User();
    }

    @GetMapping("/categories")
    public String categories()
    {
        return "sections/categories";
    }

    @GetMapping("/best")
    public String bestProjects()
    {
        return "sections/best";
    }

    @GetMapping("/register")
    public String registerPage(Model model)
    {
        model.addAttribute("UserObject", new User());
        return "register/signUpForm";
    }

    @GetMapping("/login")
    public String loginPage(Model model)
    {
        model.addAttribute("UserObject", new User());
        return "login/loginForm";
    }

    @GetMapping("/admin")
    public ModelAndView adminPage(Model model, @ModelAttribute("LoggedUser") User user)
    {
        if(user.getUsername() != null && user.getRole().equals("Admin"))
        {
            return new ModelAndView("misc/admin");
        }

        else
        {
            return new ModelAndView("redirect:/");
        }
    }

    @GetMapping("/addproject")
    public ModelAndView addProject(Model model, @ModelAttribute("LoggedUser") User user)
    {
        ModelAndView page = new ModelAndView();

        if (user.getUsername() == null)
        {
            page.setViewName("redirect:/");
            return page;
        }

        else
        {
            model.addAttribute("LoggedUser", user);
            model.addAttribute("ProjectObject", new Project());
            page.setViewName("sections/addnewproject");
            return page;
        }
    }

    @PostMapping("/openDashboard")
    public ModelAndView dashboard(Model model, @ModelAttribute("LoggedUser") User user)
    {
        ModelAndView dashboard = new ModelAndView();
        dashboard.setViewName("redirect:/dashboard");
        return dashboard;
    }
}