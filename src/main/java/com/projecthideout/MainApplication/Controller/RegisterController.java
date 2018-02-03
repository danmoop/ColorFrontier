package com.projecthideout.MainApplication.Controller;

import com.projecthideout.MainApplication.Model.User;
import com.projecthideout.MainApplication.Service.RegisterInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

@Controller
public class RegisterController
{
    @Autowired
    RegisterInterface registerInterface;

    MessageDigest messageDigest = MessageDigest.getInstance("MD5");

    public RegisterController() throws NoSuchAlgorithmException {}

    @GetMapping("/register")
    public ModelAndView registerPage(Model model)
    {
        model.addAttribute("UserObject", new User());

        return new ModelAndView("register/register");
    }

    @PostMapping("/register")
    public ModelAndView registrationAttempt(Model model, @ModelAttribute User user)
    {

        User existingUser = registerInterface.findByUsername(user.getUsername());

        if(existingUser == null)
        {
            String input = user.getPassword();
            byte[] md5sum = messageDigest.digest(input.getBytes());
            String output = String.format("%032X", new BigInteger(1, md5sum));

            String md5Password = output.toLowerCase();

            registerInterface.save(
                    new User(
                            user.getUsername(),
                            user.getEmail(),
                            md5Password,
                            new ArrayList<>(),
                            false,
                            0,
                            0
                    )
            );

        }

        else
        {
            return new ModelAndView("register/acc_exists");
        }


        return new ModelAndView("redirect:/");
    }
}
