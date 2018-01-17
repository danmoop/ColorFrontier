package com.colorfrontier.MainApp.Controller;

import com.colorfrontier.MainApp.Model.User;
import com.colorfrontier.MainApp.Service.RegisterService.RegisterInterface;
import com.colorfrontier.MainApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    UserService userService;

    public Index() throws NoSuchAlgorithmException {
    }

    @GetMapping("/dashboard")
    public ModelAndView dashboard(Model model, @ModelAttribute(value = "LoggedUser") User user, HttpServletRequest request)
    {
        ModelAndView dashboard = new ModelAndView();
        model.addAttribute("LoggedUser", user);
        dashboard.setViewName("sections/dashboard");

        return dashboard;
    }

    @GetMapping("/")
    public ModelAndView index(Model model, @ModelAttribute(value = "LoggedUser") User user, HttpServletRequest request)
    {
        model.addAttribute("LoggedUser", user);

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
    public String adminPage()
    {
        return "misc/admin";
    }
}