package com.sakti.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sakti.dao.userDAO;
import com.sakti.model.pengunjung;

@Controller
public class LoginController {

    private userDAO userDao = new userDAO();

    @GetMapping("/login")
    public String login() {

        return "login";

    }



    @PostMapping("/login")
    public String prosesLogin(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session) {

        pengunjung user =
                userDao.login(
                        username,
                        password
                );

        if(user != null){

            session.setAttribute(
                    "userLogin",
                    user);

            return "redirect:/dashboard";
        }

        return "redirect:/login";
    }
}