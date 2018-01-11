package com.colorfrontier.MainApp.Controller;

import com.colorfrontier.MainApp.Model.User;
import com.colorfrontier.MainApp.Service.RegisterService.RegisterInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController
{
    @Autowired
    RegisterInterface registerInterface;

    @PostMapping("/")
    public String loginSuccess(Model model, User user)
    {
        User userInDb = registerInterface.findByUsername(user.getUsername());

        if(user.getPassword().equals(userInDb.getPassword()) && !userInDb.getBanned())
        {
            System.out.println("LOGGED IN SUCCESSFULLY");
            return "sections/index";
        }

        else if (userInDb.getBanned())
        {
            return "misc/youarebanned";
        }

        else
        {
            return "login/loginfailed";
        }

    }
}
