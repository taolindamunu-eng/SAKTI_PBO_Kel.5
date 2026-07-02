package com.sakti.model;

import java.sql.Timestamp;

public class TransaksiView {

    private String idTransaksi;
    private String namaPengunjung;
    private String namaWisata;
    private int jumlahTiket;
    private int totalBayar;
    private String status;
    private Timestamp tanggal;

    public TransaksiView() {
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getNamaPengunjung() {
        return namaPengunjung;
    }

    public void setNamaPengunjung(String namaPengunjung) {
        this.namaPengunjung = namaPengunjung;
    }

    public String getNamaWisata() {
        return namaWisata;
    }

    public void setNamaWisata(String namaWisata) {
        this.namaWisata = namaWisata;
    }

    public int getJumlahTiket() {
        return jumlahTiket;
    }

    public void setJumlahTiket(int jumlahTiket) {
        this.jumlahTiket = jumlahTiket;
    }

    public int getTotalBayar() {
        return totalBayar;
    }

    public void setTotalBayar(int totalBayar) {
        this.totalBayar = totalBayar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getTanggal() {
        return tanggal;
    }

    public void setTanggal(Timestamp tanggal) {
        this.tanggal = tanggal;
    }

}