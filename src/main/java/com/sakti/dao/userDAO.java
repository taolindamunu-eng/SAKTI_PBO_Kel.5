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

    // REGISTER PENGUNJUNG
    public boolean register(String nama,
                            String username,
                            String password) {

        String sql =
                "INSERT INTO users(nama, username, password, role) "
              + "VALUES (?, ?, ?, ?)";

        try {

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(1, nama);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.setString(4, "PENGUNJUNG");

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            System.out.println("Gagal Registrasi");
            System.out.println(e.getMessage());

            return false;
        }
    }

    // LOGIN
    public pengunjung login(String username,
                        String password) {

    String sql =
        "SELECT * FROM users WHERE username=? AND password=?";

    try {

        PreparedStatement ps = conn.prepareStatement(sql);

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

        System.out.println(e.getMessage());

    }

    return null;

    }
    public int getIdUser(String username, String password) {
    String sql = "SELECT id_user FROM users WHERE username=? AND password=?";

    try {
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt("id_user");
        }

    } catch (Exception e) {
        System.out.println(e.getMessage());
    }

    return -1;
}
}