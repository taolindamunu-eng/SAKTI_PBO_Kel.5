package com.sakti.model;

public final class transaksi {

    private String idTransaksi;
    private pemesanan_tiket data;
    private int totalBayar;
    private int uangDiterima;

    public transaksi() {
    }

    public transaksi(String idTransaksi,
                     pemesanan_tiket data,
                     int totalBayar) {

        this.idTransaksi = idTransaksi;
        this.data = data;
        this.totalBayar = totalBayar;
    }

    public boolean prosesBayarPas(int uang) {

        uangDiterima = uang;

        return uangDiterima == totalBayar;
    }

    public void cetakStruk() {

        System.out.println("\n===== STRUK PEMESANAN =====");
        System.out.println("ID Transaksi : " + idTransaksi);
        System.out.println("Nama Wisata  : " +
                data.getWisata().getNamaWisata());
        System.out.println("Jumlah Tiket : " +
                data.getJumlahTiket());
        System.out.println("Total Bayar  : Rp " +
                totalBayar);
        System.out.println("STATUS       : LUNAS");
        System.out.println("===========================\n");
    }
}