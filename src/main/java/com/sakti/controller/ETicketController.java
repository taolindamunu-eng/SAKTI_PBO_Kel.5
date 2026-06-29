package com.sakti.controller;

import java.sql.ResultSet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sakti.dao.transaksiDAO;

@Controller
public class ETicketController {

    private transaksiDAO dao =
            new transaksiDAO();

    @GetMapping("/eticket/{id}")
    public String eticket(

            @PathVariable("id")
            String id,

            Model model){

        try{

            ResultSet rs =
                    dao.getETicket(id);

            if(rs.next()){

                model.addAttribute(
                        "id",
                        rs.getString(
                                "id_transaksi"));

                model.addAttribute(
                        "nama",
                        rs.getString(
                                "nama"));

                model.addAttribute(
                        "wisata",
                        rs.getString(
                                "nama_wisata"));

                model.addAttribute(
                        "harga",
                        rs.getInt(
                                "harga_tiket"));

                model.addAttribute(
                        "jumlah",
                        rs.getInt(
                                "jumlah_tiket"));

                model.addAttribute(
                        "total",
                        rs.getInt(
                                "total_bayar"));

                model.addAttribute(
                        "status",
                        rs.getString(
                                "status"));

                model.addAttribute(
                        "tanggal",
                        rs.getTimestamp(
                                "tanggal"));
            }

        }catch(Exception e){

            e.printStackTrace();
        }

        return "eticket";
    }
}