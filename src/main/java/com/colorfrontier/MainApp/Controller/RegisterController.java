package com.colorfrontier.MainApp.Controller;

import com.colorfrontier.MainApp.Model.User;
import com.colorfrontier.MainApp.Service.RegisterInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;

@Controller
public class RegisterController
{
    @Autowired
    RegisterInterface registerInterface;

    MessageDigest messageDigest = MessageDigest.getInstance("MD5");

    public RegisterController() throws NoSuchAlgorithmException {}

    @PostMapping("/regsuccess")
    public String regSuccessPost(Model model, User user) throws NoSuchAlgorithmException {

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
                        false, new ArrayList<>(),
                        "User", 0
            ));

            return "register/register_success";
        }

        else
        {
            return "misc/accexists";
        }
    }
}