package com.sakti.model;

public class admin extends user {

    public admin() {
    }

    public admin(int id, String username, String password, String nama) {
        super(id, username, password, nama);
    }

    @Override
    public void tampilkanMenu() {
        System.out.println("===== MENU ADMIN =====");
    }

    public void tambahDestinasi() {
        System.out.println("Tambah destinasi wisata");
    }

    public void lihatRekapTransaksi() {
        System.out.println("Melihat rekap transaksi");
    }
}