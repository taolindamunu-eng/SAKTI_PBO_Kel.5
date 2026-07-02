package com.sakti.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sakti.config.DatabaseConnection;
import com.sakti.model.pengunjung;

public class userDAO {

    private Connection conn;

    public userDAO() {
        conn = DatabaseConnection.getConnection();
    }

    // ================= REGISTER =================

    public boolean register(String nama,
                            String username,
                            String password) {

        String sql =
                "INSERT INTO users (nama, username, password, role) VALUES (?, ?, ?, ?)";

        try {

            if (conn == null) {
                System.out.println("DATABASE BELUM TERKONEKSI");
                return false;
            }

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, nama);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.setString(4, "PENGUNJUNG");

            int hasil = ps.executeUpdate();

            System.out.println("==============================");
            System.out.println("HASIL INSERT = " + hasil);
            System.out.println("NAMA      = " + nama);
            System.out.println("USERNAME  = " + username);
            System.out.println("PASSWORD  = " + password);
            System.out.println("==============================");

            return hasil > 0;

        } catch (Exception e) {

            System.out.println("==============================");
            System.out.println("REGISTER GAGAL");
            e.printStackTrace();
            System.out.println("==============================");

            return false;
        }
    }

    // ================= LOGIN =================

    public pengunjung login(String username,
                            String password) {

        String sql =
                "SELECT * FROM users WHERE username=? AND password=?";

        try {

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                pengunjung p = new pengunjung();

                p.setId(rs.getInt("id_user"));
                p.setNama(rs.getString("nama"));
                p.setUsername(rs.getString("username"));
                p.setPassword(rs.getString("password"));
                p.setRole(rs.getString("role"));

                return p;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // ================= GET ID USER =================

    public int getIdUser(String username,
                         String password) {

        String sql =
                "SELECT id_user FROM users WHERE username=? AND password=?";

        try {

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("id_user");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

}