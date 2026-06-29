package com.sakti.model;

public class destinasi_wisata {

    private String idWisata;
    private String namaWisata;
    private detail_destinasi_wisata detail;

    public destinasi_wisata() {
    }

    public destinasi_wisata(String idWisata,
                            String namaWisata,
                            detail_destinasi_wisata detail) {
        this.idWisata = idWisata;
        this.namaWisata = namaWisata;
        this.detail = detail;
    }

    public String getIdWisata() {
        return idWisata;
    }

    public void setIdWisata(String idWisata) {
        this.idWisata = idWisata;
    }

    public String getNamaWisata() {
        return namaWisata;
    }

    public void setNamaWisata(String namaWisata) {
        this.namaWisata = namaWisata;
    }

    public detail_destinasi_wisata getDetail() {
        return detail;
    }

    public void setDetail(detail_destinasi_wisata detail) {
        this.detail = detail;
    }
}