package com.colorfrontier.MainApp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Index
{
    @GetMapping("/")
    public String index()
    {
        return "index";
    }

    @GetMapping("/categories")
    public String categories()
    {
        return "categories";
    }

    @GetMapping("/best")
    public String bestProjects()
    {
        return "best";
    }

    @GetMapping("/register")
    public String registerPage()
    {
        return "register/signUpForm";
    }

    @GetMapping("/login")
    public String loginPage()
    {
        return "register/loginForm";
    }
}
