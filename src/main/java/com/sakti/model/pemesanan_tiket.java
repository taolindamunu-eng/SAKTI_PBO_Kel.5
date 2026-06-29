package com.sakti.model;

public class pemesanan_tiket {

    private pengunjung pembeli;
    private destinasi_wisata wisata;
    private int jumlahTiket;

    public pemesanan_tiket() {
    }

    public pemesanan_tiket(pengunjung pembeli, destinasi_wisata wisata, int jumlahTiket) {
        this.pembeli = pembeli;
        this.wisata = wisata;
        this.jumlahTiket = jumlahTiket;
    }

    public int hitungTotalSem() {
        return wisata.getDetail().getHargaTiket() * jumlahTiket;
    }

    public pengunjung getPembeli() {
        return pembeli;
    }

    public void setPembeli(pengunjung pembeli) {
        this.pembeli = pembeli;
    }

    public destinasi_wisata getWisata() {
        return wisata;
    }

    public void setWisata(destinasi_wisata wisata) {
        this.wisata = wisata;
    }

    public int getJumlahTiket() {
        return jumlahTiket;
    }

    public void setJumlahTiket(int jumlahTiket) {
        this.jumlahTiket = jumlahTiket;
    }
}