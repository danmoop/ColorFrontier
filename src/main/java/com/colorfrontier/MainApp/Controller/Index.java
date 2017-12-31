package com.colorfrontier.MainApp.Controller;

import com.colorfrontier.MainApp.Model.User;
import com.colorfrontier.MainApp.Service.RegisterService.RegisterInterface;
import com.colorfrontier.MainApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.security.NoSuchAlgorithmException;

@Controller
public class Index
{

    @Autowired
    RegisterInterface registerInterface;

    @Autowired
    UserService userService;

    public Index() throws NoSuchAlgorithmException {
    }

    @GetMapping("/")
    public String index()
    {
        return "sections/index";
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
    public String loginPage()
    {
        return "register/loginForm";
    }

    @GetMapping("/admin")
    public String adminPage()
    {
        return "admin";
    }

    @PostMapping("/regsuccess")
    public String regSuccessPost(Model model, User user) throws NoSuchAlgorithmException {
        System.out.println("");
        System.out.println("Email: " + user.getEmail());
        System.out.println("Username: " + user.getUsername());
        System.out.println("Password: " + user.getPassword());

        registerInterface.save(
                new User(user.getUsername(), user.getEmail(), user.getPassword(), false
        ));

        return "register/register_success";
    }
}
