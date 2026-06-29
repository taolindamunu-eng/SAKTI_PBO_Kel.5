package com.sakti.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sakti.dao.destinasiDAO;
import com.sakti.model.destinasi_wisata;
import com.sakti.model.pengunjung;

@Controller
public class DestinasiController {

    private destinasiDAO dao =
            new destinasiDAO();

    // DAFTAR DESTINASI
    @GetMapping("/destinasi")
    public String tampilDestinasi(
            HttpSession session,
            Model model){

        pengunjung user =
                (pengunjung)
                session.getAttribute("userLogin");

        if(user == null){
            return "redirect:/login";
        }

        List<destinasi_wisata> daftar =
                dao.getAllDestinasi();

        model.addAttribute(
                "destinasi",
                daftar);

        return "destinasi";
    }

    // DETAIL DESTINASI
    @GetMapping("/destinasi/{id}")
    public String detailDestinasi(
            @PathVariable("id") String id,
            HttpSession session,
            Model model){

        System.out.println("ID = " + id);

        pengunjung user =
                (pengunjung)
                session.getAttribute("userLogin");

        if(user == null){
            return "redirect:/login";
        }

        destinasi_wisata wisata =
                dao.cariDestinasi(id);

        System.out.println(wisata);

        if(wisata == null){
            System.out.println("DATA TIDAK ADA");
            return "redirect:/wisata";
        }

        model.addAttribute(
                "wisata",
                wisata);

        return "detail";
    }
}