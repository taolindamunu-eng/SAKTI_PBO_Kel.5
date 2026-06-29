package com.sakti.report;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import com.sakti.config.DatabaseConnection;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;

public class ReportTiketPengunjung {

    public void cetak(String idTransaksi) {

        try {

            // koneksi database
            Connection conn = DatabaseConnection.getConnection();

            // ambil file jrxml dari resources
            InputStream file =
                    getClass().getResourceAsStream("/report/tiket_pengunjung.jrxml");

            // cek kalau file tidak ditemukan
            if (file == null) {
                System.out.println("File JRXML tidak ditemukan di resources!");
                return;
            }

            // compile report dari stream (INI YANG BENAR)
            JasperReport report =
                    JasperCompileManager.compileReport(file);

            // parameter untuk report
            HashMap<String, Object> param = new HashMap<>();

            param.put("id_transaksi", idTransaksi);

            // isi report dengan data
            JasperPrint print =
                    JasperFillManager.fillReport(report, param, conn);

            // tampilkan report
            JasperViewer.viewReport(print, false);

        } catch (Exception e) {
            System.out.println("Error Report: " + e.getMessage());
        }
    }
}