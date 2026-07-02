package com.sakti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sakti.model.pengunjung;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

    @GetMapping("/admin/dashboard")
    public String dashboardAdmin(
            HttpSession session,
            Model model) {

        pengunjung user =
                (pengunjung) session.getAttribute("userLogin");

        // Jika belum login
        if (user == null) {
            return "redirect:/login";
        }

        // Jika bukan ADMIN
        if (!"ADMIN".equalsIgnoreCase(user.getRole())) {
            return "redirect:/dashboard";
        }

        model.addAttribute("nama", user.getNama());

        return "admin/dashboard";
    }
}