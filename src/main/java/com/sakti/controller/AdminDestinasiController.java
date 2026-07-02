package com.sakti.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.sakti.dao.destinasiDAO;
import com.sakti.model.destinasi_wisata;
import com.sakti.model.pengunjung;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminDestinasiController {

    private destinasiDAO dao = new destinasiDAO();

    @GetMapping("/admin/destinasi")
    public String destinasi(HttpSession session, Model model) {

        pengunjung user = (pengunjung) session.getAttribute("userLogin");

        if (user == null)
            return "redirect:/login";

        if (!"ADMIN".equalsIgnoreCase(user.getRole()))
            return "redirect:/dashboard";

        model.addAttribute("nama", user.getNama());
        model.addAttribute("destinasi", dao.getAllDestinasi());

        return "admin/destinasi";
    }

    @GetMapping("/admin/destinasi/tambah")
    public String tambah(HttpSession session) {

        pengunjung user = (pengunjung) session.getAttribute("userLogin");

        if (user == null)
            return "redirect:/login";

        return "admin/tambah-destinasi";
    }

    @PostMapping("/admin/destinasi/simpan")
    public String simpan(

            @RequestParam String idWisata,
            @RequestParam String namaWisata,
            @RequestParam int harga,
            @RequestParam String lokasi,
            @RequestParam String jam) {

        dao.tambahDestinasi(
                idWisata,
                namaWisata,
                harga,
                lokasi,
                jam);

        return "redirect:/admin/destinasi";
    }

    @GetMapping("/admin/destinasi/edit/{id}")
    public String edit(

            @PathVariable String id,
            Model model) {

        model.addAttribute(
                "wisata",
                dao.cariDestinasi(id));

        return "admin/edit-destinasi";
    }

    @PostMapping("/admin/destinasi/update")
    public String update(

            @RequestParam String idWisata,
            @RequestParam String namaWisata,
            @RequestParam int harga,
            @RequestParam String lokasi,
            @RequestParam String jam) {

        dao.editDestinasiWeb(
                idWisata,
                namaWisata,
                harga,
                lokasi,
                jam);

        return "redirect:/admin/destinasi";
    }

    @GetMapping("/admin/destinasi/hapus/{id}")
    public String hapus(@PathVariable String id) {

        dao.hapusDestinasiWeb(id);

        return "redirect:/admin/destinasi";
    }
 

}