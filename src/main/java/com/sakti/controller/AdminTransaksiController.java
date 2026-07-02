package com.sakti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sakti.dao.transaksiDAO;
import com.sakti.model.pengunjung;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminTransaksiController {

    private transaksiDAO dao = new transaksiDAO();

    @GetMapping("/admin/transaksi")
    public String transaksi(
            HttpSession session,
            Model model) {

        pengunjung user =
                (pengunjung) session.getAttribute("userLogin");

        if (user == null) {
            return "redirect:/login";
        }

        if (!"ADMIN".equalsIgnoreCase(user.getRole())) {
            return "redirect:/dashboard";
        }

        model.addAttribute("nama", user.getNama());
        model.addAttribute("transaksi", dao.getAllTransaksi());

        return "admin/transaksi";
    }

}