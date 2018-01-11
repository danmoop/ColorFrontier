package com.colorfrontier.MainApp.Controller;

import com.colorfrontier.MainApp.Model.User;
import com.colorfrontier.MainApp.Service.RegisterService.RegisterInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.NoSuchAlgorithmException;
import java.util.HashSet;

@Controller
public class RegisterController
{
    @Autowired
    RegisterInterface registerInterface;

    @PostMapping("/regsuccess")
    public String regSuccessPost(Model model, User user) throws NoSuchAlgorithmException {
        System.out.println("");
        System.out.println("Email: " + user.getEmail());
        System.out.println("Username: " + user.getUsername());
        System.out.println("Password: " + user.getPassword());

        registerInterface.save(
            new User(user.getUsername(), user.getEmail(), user.getPassword(), false, new HashSet<>()
        ));

        return "register/register_success";
    }
}
