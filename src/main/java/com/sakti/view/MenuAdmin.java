package com.sakti.view;

import java.util.Scanner;

import com.sakti.dao.destinasiDAO;
import com.sakti.dao.transaksiDAO;

public class MenuAdmin {

    private Scanner input = new Scanner(System.in);
    private destinasiDAO destinasiDAO = new destinasiDAO();
    private transaksiDAO transaksiDAO = new transaksiDAO();

    // Method pertama
    public void tampilkanMenu() {

    int pilih;

    do {

        System.out.println("===== MENU ADMIN =====");
        System.out.println("1. Lihat Daftar Destinasi");
        System.out.println("2. Lihat Rekap Transaksi");
        System.out.println("3. Cetak Daftar Destinasi");
        System.out.println("4. Cetak Riwayat Transaksi");
        System.out.println("0. Logout");
        System.out.print("Pilih Menu : ");

        pilih = input.nextInt();
        input.nextLine();

        switch (pilih) {

            case 1:
                menuDestinasi();   // Memanggil method yang Anda buat
                break;

            case 2:
                transaksiDAO.lihatRekapTransaksi();
                break;

            case 0:
                System.out.println("Logout berhasil");
                break;
        }

    } while (pilih != 0);
}

    // Method kedua
    private void menuDestinasi() {

        int pilih;

        do {

            destinasiDAO.lihatDestinasi();

            System.out.println("\n===== MANAJEMEN DESTINASI =====");
            System.out.println("1. Tambah Destinasi");
            System.out.println("2. Edit Destinasi");
            System.out.println("3. Hapus Destinasi");
            System.out.println("0. Kembali");
            System.out.print("Pilih Menu : ");

            pilih = input.nextInt();
            input.nextLine();

            switch (pilih) {

                case 1:
                    destinasiDAO.tambahDestinasi();
                    break;

                case 2:
                    destinasiDAO.editDestinasi();
                    break;

                case 3:
                    destinasiDAO.hapusDestinasi();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Menu tidak tersedia");
            }

        } while (pilih != 0);
    }
}