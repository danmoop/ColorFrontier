package com.colorfrontier.MainApp.Controller;

import com.colorfrontier.MainApp.Service.RegisterInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes(value = "LoggedUser")
public class UserController
{
    @Autowired
    RegisterInterface registerInterface;

    @GetMapping("/user/{username}")
    public ModelAndView findUser(@PathVariable String username, Model model)
    {
        model.addAttribute("UserObject", registerInterface.findByUsername(username));

        return new ModelAndView("sections/viewUserDashboard");
    }
}
