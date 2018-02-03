package com.projecthideout.MainApplication.Controller;

import com.projecthideout.MainApplication.Model.User;
import com.projecthideout.MainApplication.Service.RegisterInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes(value = "LoggedUser")
public class Index
{

    @Autowired
    RegisterInterface registerInterface;

    @ModelAttribute(value = "LoggedUser")
    public User nullUser()
    {
        return new User();
    }

    @GetMapping("/")
    public ModelAndView indexPage(Model model, @ModelAttribute(value = "LoggedUser") User user)
    {
        model.addAttribute("LoggedUser", user);

        return new ModelAndView("index");
    }

    @GetMapping("/dashboard")
    public ModelAndView dashboard(Model model, @ModelAttribute(value = "LoggedUser") User user)
    {
        model.addAttribute("LoggedUser", user);
        return new ModelAndView("sections/dashboard");
    }

    @GetMapping("/user/{username}")
    public ModelAndView userdashboard(Model model, @PathVariable String username)
    {
        User user = registerInterface.findByUsername(username);

        model.addAttribute("UserObject", user);

        return new ModelAndView("sections/userpage");
    }
}
