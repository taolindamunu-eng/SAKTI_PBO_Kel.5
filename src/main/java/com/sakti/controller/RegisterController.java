package com.sakti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sakti.dao.userDAO;

@Controller
public class RegisterController {

    private userDAO userDao = new userDAO();

    @GetMapping("/register")
    public String register() {

        return "register";

    }

    @PostMapping("/register")
    public String prosesRegister(
             @RequestParam("nama")
            String nama,

            @RequestParam("username")
            String username,

            @RequestParam("password")
            String password,

            @RequestParam("konfirmasi")
            String konfirmasi){

        if (!password.equals(konfirmasi)) {

            return "redirect:/register";

        }

        boolean berhasil =
                userDao.register(
                        nama,
                        username,
                        password
                );

        if (berhasil) {

            return "redirect:/login";

        }

        return "redirect:/register";
    }
}