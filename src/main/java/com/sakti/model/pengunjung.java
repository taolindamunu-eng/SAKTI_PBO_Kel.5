package com.sakti.model;

public class pengunjung extends user {

    public pengunjung() {
    }

    public pengunjung(int id, String username, String password, String nama) {
        super(id, username, password, nama);
    }

    @Override
    public void tampilkanMenu() {
        System.out.println("===== MENU PENGUNJUNG =====");
    }

    public void registrasiAkun() {
        System.out.println("Registrasi akun");
    }

    public void lihatDaftarWisata() {
        System.out.println("Menampilkan daftar wisata");
    }
}