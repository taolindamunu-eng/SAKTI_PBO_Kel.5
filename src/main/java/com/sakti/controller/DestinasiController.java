package com.sakti.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.sakti.dao.destinasiDAO;
import com.sakti.model.destinasi_wisata;
import com.sakti.model.pengunjung;

import jakarta.servlet.http.HttpSession;

@Controller
public class DestinasiController {

    private destinasiDAO dao = new destinasiDAO();

    // =========================
    // DAFTAR DESTINASI
    // =========================
    @GetMapping("/destinasi")
    public String tampilDestinasi(

            @RequestParam(value = "keyword", required = false)
            String keyword,

            HttpSession session,

            Model model) {

        pengunjung user =
                (pengunjung) session.getAttribute("userLogin");

        if (user == null) {
            return "redirect:/login";
        }

        List<destinasi_wisata> daftar;

        if (keyword == null || keyword.isEmpty()) {

            daftar = dao.getAllDestinasi();

        } else {

            daftar = dao.cariDestinasiByNama(keyword);

        }

        model.addAttribute("destinasi", daftar);
        model.addAttribute("keyword", keyword);

        return "destinasi";
    }

    // =========================
    // DETAIL DESTINASI
    // =========================
    @GetMapping("/destinasi/{id}")
    public String detailDestinasi(

            @PathVariable("id")
            String id,

            HttpSession session,

            Model model) {

        pengunjung user =
                (pengunjung) session.getAttribute("userLogin");

        if (user == null) {
            return "redirect:/login";
        }

        destinasi_wisata destinasi =
                dao.cariDestinasi(id);

        if (destinasi == null) {
            return "redirect:/destinasi";
        }

        model.addAttribute("destinasi", destinasi);

        return "detail";
    }

}