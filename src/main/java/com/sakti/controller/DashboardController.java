package com.sakti.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sakti.model.pengunjung;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(
            HttpSession session,
            Model model) {

        pengunjung user =
                (pengunjung)
                session.getAttribute(
                        "userLogin");

        // kalau belum login
        if(user == null){

            return "redirect:/login";
        }

        model.addAttribute(
                "nama",
                user.getNama());

        return "dashboard";
    }

}