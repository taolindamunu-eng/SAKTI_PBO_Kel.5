package com.sakti.controller;

import java.util.UUID;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sakti.dao.destinasiDAO;
import com.sakti.dao.transaksiDAO;
import com.sakti.model.destinasi_wisata;
import com.sakti.model.pengunjung;

@Controller
public class PemesananController {

    private transaksiDAO transaksiDao =
            new transaksiDAO();

    private destinasiDAO destinasiDao =
            new destinasiDAO();

    @PostMapping("/pesan")
    public String prosesPemesanan(

            @RequestParam("idWisata")
            String idWisata,

            @RequestParam("jumlah")
            int jumlah,

            HttpSession session){

        // ambil user login
        pengunjung user =
                (pengunjung)
                session.getAttribute(
                        "userLogin");

        if(user == null){
            return "redirect:/login";
        }

        // ambil data wisata
        destinasi_wisata wisata =
                destinasiDao
                .cariDestinasi(idWisata);

        if(wisata == null){
            return "redirect:/wisata";
        }

        // hitung total
        int total =
                wisata
                .getDetail()
                .getHargaTiket()
                * jumlah;

        // generate id transaksi
        String idTransaksi =
                "TRX" +
                UUID.randomUUID()
                        .toString()
                        .substring(0,8);

        // simpan transaksi
        transaksiDao.simpanTransaksi(
                idTransaksi,
                user.getId(),
                wisata.getIdWisata(),
                jumlah,
                total,
                "BERHASIL"
        );

        // nanti diarahkan ke e-ticket
        return "redirect:/eticket/" + idTransaksi;
    }
}