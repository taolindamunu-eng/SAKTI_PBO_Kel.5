package com.sakti.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sakti.dao.transaksiDAO;
import com.sakti.model.pengunjung;

@Controller
public class AdminTransaksiDetailController {

    private transaksiDAO dao =
            new transaksiDAO();

    @GetMapping("/admin/transaksi/detail/{id}")
    public String detail(

            @PathVariable String id,
            HttpSession session,
            Model model){

        pengunjung admin =
                (pengunjung)
                session.getAttribute("userLogin");

        if(admin==null){

            return "redirect:/login";

        }

        model.addAttribute(
                "transaksi",
                dao.getTransaksiById(id));

        return "admin/detail-transaksi";

    }
    @PostMapping("/admin/transaksi/update")
public String updateStatus(

        @RequestParam String idTransaksi,
        @RequestParam String status){

    dao.updateStatus(idTransaksi, status);

    return "redirect:/admin/transaksi";

}

}