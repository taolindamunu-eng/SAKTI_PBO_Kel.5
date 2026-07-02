package com.sakti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sakti.dao.destinasiDAO;
import com.sakti.model.destinasi_wisata;
import com.sakti.model.pengunjung;

import jakarta.servlet.http.HttpSession;

@Controller
public class PesanController {

    private destinasiDAO destinasiDao = new destinasiDAO();

    @GetMapping("/pesan/{id}")
    public String formPesan(

            @PathVariable("id")
            String id,

            HttpSession session,

            Model model) {

        pengunjung user =
                (pengunjung) session.getAttribute("userLogin");

        if (user == null) {
            return "redirect:/login";
        }

        destinasi_wisata wisata =
                destinasiDao.cariDestinasi(id);

        if (wisata == null) {
            return "redirect:/destinasi";
        }

        model.addAttribute("wisata", wisata);

        return "pesan";
    }

}