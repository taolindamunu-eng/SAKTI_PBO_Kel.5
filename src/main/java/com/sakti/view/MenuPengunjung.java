package com.sakti.view;

import java.util.Scanner;

import com.sakti.dao.destinasiDAO;
import com.sakti.dao.transaksiDAO;
import com.sakti.model.destinasi_wisata;
import com.sakti.model.pemesanan_tiket;
import com.sakti.model.pengunjung;

public class MenuPengunjung {

    private pengunjung userLogin;
    private Scanner input = new Scanner(System.in);
    private destinasiDAO destinasiDAO = new destinasiDAO();

    public MenuPengunjung(pengunjung userLogin) {
        this.userLogin = userLogin;
    }

    private void pesanTiket() {

        destinasiDAO.lihatDestinasi();

        System.out.print("\nMasukkan ID Wisata : ");
        String id = input.nextLine();

        destinasi_wisata wisata = destinasiDAO.cariDestinasi(id);

        if (wisata == null) {
            System.out.println("ID wisata tidak ditemukan.");
            return;
        }

        System.out.print("Jumlah tiket : ");
        int jumlah = input.nextInt();
        input.nextLine();

        if (jumlah <= 0) {
            System.out.println("Jumlah tiket tidak valid.");
            return;
        }

        pemesanan_tiket pesanan = new pemesanan_tiket();

        pesanan.setWisata(wisata);
        pesanan.setJumlahTiket(jumlah);

        System.out.println("\n===== RINGKASAN PESANAN =====");
        System.out.println("Wisata : " + wisata.getNamaWisata());
        System.out.println("Harga  : Rp" + wisata.getDetail().getHargaTiket());
        System.out.println("Jumlah : " + jumlah);
        System.out.println("Total  : Rp" + pesanan.hitungTotalSem());

        System.out.print("\nKonfirmasi (Y/T): ");
        String jawab = input.nextLine();

        if (jawab.equalsIgnoreCase("Y")) {

            transaksiDAO transaksiDAO = new transaksiDAO();

            String idTransaksi =
                    "TRX" + System.currentTimeMillis();

            transaksiDAO.simpanTransaksi(
                    idTransaksi,
                    userLogin.getId(),
                    wisata.getIdWisata(),
                    jumlah,
                    pesanan.hitungTotalSem(),
                    "BERHASIL"
            );

            System.out.println("\nPesanan berhasil!");
            System.out.println("Silakan download E-Ticket melalui aplikasi web.");

        } else {

            System.out.println("Pesanan dibatalkan.");

        }

    }

    public void tampilkanMenu() {

        int pilih;

        do {

            System.out.println("\n===== MENU PENGUNJUNG =====");
            System.out.println("1. Lihat Daftar Wisata");
            System.out.println("2. Pesan Tiket");
            System.out.println("0. Logout");
            System.out.print("Pilih Menu : ");

            pilih = input.nextInt();
            input.nextLine();

            switch (pilih) {

                case 1:
                    destinasiDAO.lihatDestinasi();
                    break;

                case 2:
                    pesanTiket();
                    break;

                case 0:
                    System.out.println("Logout berhasil");
                    break;

                default:
                    System.out.println("Menu tidak tersedia");
                    break;
            }

        } while (pilih != 0);

    }

}