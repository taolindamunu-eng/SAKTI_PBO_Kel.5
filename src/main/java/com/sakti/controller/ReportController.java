package com.sakti.controller;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sakti.config.DatabaseConnection;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@RestController
public class ReportController {

    @GetMapping("/report/{id}")
    public ResponseEntity<byte[]> downloadPdf(
            @PathVariable("id") String id) {

        try {

            Connection conn = DatabaseConnection.getConnection();

            InputStream file = getClass().getResourceAsStream(
                    "/report/tiket_pengunjung.jrxml");

            if (file == null) {
                throw new RuntimeException(
                        "File tiket_pengunjung.jrxml tidak ditemukan di folder resources/report");
            }

            JasperReport report =
                    JasperCompileManager.compileReport(file);

            HashMap<String, Object> param =
                    new HashMap<>();

            param.put("id_transaksi", id);

            JasperPrint print =
                    JasperFillManager.fillReport(
                            report,
                            param,
                            conn);

            byte[] pdf =
                    JasperExportManager.exportReportToPdf(print);

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=ETicket.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdf);

        } catch (Exception e) {

            e.printStackTrace();

            String pesan = e.toString();

            return ResponseEntity.status(500)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(pesan.getBytes());
        }
    }
}