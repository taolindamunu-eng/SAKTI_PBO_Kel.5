package com.sakti.model;

public class detail_destinasi_wisata {

    private int hargaTiket;
    private String lokasi;
    private String jamOperasional;

    public detail_destinasi_wisata() {
    }

    public detail_destinasi_wisata(int hargaTiket,
                                  String lokasi,
                                  String jamOperasional) {
        this.hargaTiket = hargaTiket;
        this.lokasi = lokasi;
        this.jamOperasional = jamOperasional;
    }

    public int getHargaTiket() {
        return hargaTiket;
    }

    public void setHargaTiket(int hargaTiket) {
        this.hargaTiket = hargaTiket;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getJamOperasional() {
        return jamOperasional;
    }

    public void setJamOperasional(String jamOperasional) {
        this.jamOperasional = jamOperasional;
    }
}