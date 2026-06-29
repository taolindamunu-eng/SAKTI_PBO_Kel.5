package com.sakti.view;

import java.util.Scanner;

import com.sakti.dao.userDAO;
import com.sakti.model.pengunjung;

public class MenuUtama {

    private Scanner input = new Scanner(System.in);
    private userDAO userDao = new userDAO();

    public void tampilkan() {

        int pilihan;

        do {

            System.out.println("\n=================================");
            System.out.println("         SISTEM SAKTI");
            System.out.println("=================================");
            System.out.println("1. Login");
            System.out.println("2. Registrasi");
            System.out.println("0. Keluar");
            System.out.print("Pilih Menu : ");

            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {

                case 1:
                    login();
                    break;

                case 2:
                    registrasi();
                    break;

                case 0:
                    System.out.println("\nTerima kasih telah menggunakan SAKTI.");
                    break;

                default:
                    System.out.println("\nMenu tidak tersedia!");
            }

        } while (pilihan != 0);
    }

    private void registrasi() {

        System.out.println("\n===== REGISTRASI =====");

        System.out.print("Nama      : ");
        String nama = input.nextLine();

        System.out.print("Username  : ");
        String username = input.nextLine();

        System.out.print("Password  : ");
        String password = input.nextLine();

        boolean berhasil =
                userDao.register(
                        nama,
                        username,
                        password
                );

        if (berhasil) {

            System.out.println("\nRegistrasi Berhasil!");

        } else {

            System.out.println("\nRegistrasi Gagal!");
        }
    }

    private void login() {


        System.out.println("\n===== LOGIN =====");

        System.out.print("Username : ");
        String username = input.nextLine();

        System.out.print("Password : ");
        String password = input.nextLine();

        pengunjung user = userDao.login(username, password);

        if (user != null) {

            System.out.println("\nLogin Berhasil!");
            System.out.println("Role : " + user.getRole());

            if (user.getRole().equalsIgnoreCase("ADMIN")) {

                MenuAdmin admin = new MenuAdmin();
                admin.tampilkanMenu();

            } else {

                MenuPengunjung mp = new MenuPengunjung(user);
                mp.tampilkanMenu();
            }

        }
        }
        
}
