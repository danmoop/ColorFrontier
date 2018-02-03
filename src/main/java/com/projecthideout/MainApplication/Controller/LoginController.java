package com.projecthideout.MainApplication.Controller;

import com.projecthideout.MainApplication.Model.User;
import com.projecthideout.MainApplication.Service.RegisterInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Controller
@SessionAttributes(value = "LoggedUser")
public class LoginController
{
    MessageDigest messageDigest = MessageDigest.getInstance("MD5");

    public LoginController() throws NoSuchAlgorithmException {}

    @Autowired
    RegisterInterface registerInterface;

    @GetMapping("/login")
    public ModelAndView loginPage(Model model)
    {
        model.addAttribute("LoggedUser", new User());

        return new ModelAndView("login/loginform");
    }

    @PostMapping("/login")
    public ModelAndView loginSuccess(Model model, @ModelAttribute(value = "LoggedUser") User user, SessionStatus sessionStatus)
    {
        User userInDb = registerInterface.findByUsername(user.getUsername());

        String input = user.getPassword();
        byte[] md5sum = messageDigest.digest(input.getBytes());
        String output = String.format("%032X", new BigInteger(1, md5sum));

        String md5Password = output.toLowerCase();

        if(userInDb != null && md5Password.equals(userInDb.getPassword()) && !userInDb.isBanned())
        {
            ModelAndView indexPage = new ModelAndView();
            model.addAttribute("LoggedUser", userInDb);
            indexPage.setViewName("redirect:/");

            return indexPage; // redirect to home page
        }

        else if (userInDb != null && userInDb.isBanned() && md5Password.equals(userInDb.getPassword()))
        {
            ModelAndView youarebanned = new ModelAndView();
            youarebanned.setViewName("misc/youarebanned");
            sessionStatus.setComplete();
            return youarebanned;
        }

        else
        {
            ModelAndView loginfailed = new ModelAndView();
            loginfailed.setViewName("login/loginfailed");
            return loginfailed;
        }
    }

    @GetMapping("/logout")
    public String logout(SessionStatus sessionStatus)
    {
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
