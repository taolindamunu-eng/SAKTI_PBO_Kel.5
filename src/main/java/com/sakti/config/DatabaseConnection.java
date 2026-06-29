package com.sakti.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/db_sakti";

    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {

        try {

            Connection conn = DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );

            System.out.println("=================================");
            System.out.println("KONEKSI DATABASE BERHASIL");
            System.out.println("Database : sakti");
            System.out.println("=================================");

            return conn;

        } catch (SQLException e) {

            System.out.println("=================================");
            System.out.println("KONEKSI DATABASE GAGAL");
            System.out.println("Pesan Error : " + e.getMessage());
            System.out.println("=================================");

            return null;
        }
    }
}