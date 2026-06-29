package com.sakti.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import com.sakti.config.DatabaseConnection;
import com.sakti.model.destinasi_wisata;
import com.sakti.model.detail_destinasi_wisata;

public class destinasiDAO {
    
    private Connection conn;
    private Scanner input = new Scanner(System.in);

    public destinasiDAO() {
        conn = DatabaseConnection.getConnection();
    }

    public void lihatDestinasi() {

        String sql =
                "SELECT dw.id_wisata, dw.nama_wisata, " +
                "dd.harga_tiket, dd.lokasi, dd.jam_operasional " +
                "FROM destinasi_wisata dw " +
                "JOIN detail_destinasi dd " +
                "ON dw.id_wisata = dd.id_wisata";

        try {

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            System.out.println("\n===== DAFTAR WISATA =====");

            while (rs.next()) {

                System.out.println("----------------------------");
                System.out.println("ID Wisata : " +
                        rs.getInt("id_wisata"));
                System.out.println("Nama      : " +
                        rs.getString("nama_wisata"));
                System.out.println("Harga     : Rp" +
                        rs.getInt("harga_tiket"));
                System.out.println("Lokasi    : " +
                        rs.getString("lokasi"));
                System.out.println("Jam       : " +
                        rs.getString("jam_operasional"));
            }

        } catch (Exception e) {

            System.out.println("Gagal menampilkan data wisata");
            System.out.println(e.getMessage());
        }
    }

    public destinasi_wisata cariDestinasi(String idWisata) {

    String sql =
        "SELECT dw.id_wisata, dw.nama_wisata, " +
        "dd.harga_tiket, dd.lokasi, dd.jam_operasional " +
        "FROM destinasi_wisata dw " +
        "JOIN detail_destinasi dd ON dw.id_wisata = dd.id_wisata " +
        "WHERE dw.id_wisata = ?";

    try {

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, idWisata);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            detail_destinasi_wisata detail =
                new detail_destinasi_wisata(
                    rs.getInt("harga_tiket"),
                    rs.getString("lokasi"),
                    rs.getString("jam_operasional")
                );

            return new destinasi_wisata(
                rs.getString("id_wisata"),
                rs.getString("nama_wisata"),
                detail
            );
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
}

    public void tambahDestinasi() {

    try {

        System.out.println("\n===== TAMBAH DESTINASI =====");

        System.out.print("ID Wisata           : ");
        String id = input.nextLine();

        System.out.print("Nama Wisata         : ");
        String nama = input.nextLine();

        System.out.print("Harga Tiket         : ");
        int harga = input.nextInt();
        input.nextLine();

        System.out.print("Lokasi              : ");
        String lokasi = input.nextLine();

        System.out.print("Jam Operasional     : ");
        String jam = input.nextLine();

        conn.setAutoCommit(false);

        String sql1 =
                "INSERT INTO destinasi_wisata VALUES (?, ?)";

        PreparedStatement ps1 =
                conn.prepareStatement(sql1);

        ps1.setString(1, id);
        ps1.setString(2, nama);
        ps1.executeUpdate();

        String sql2 =
                "INSERT INTO detail_destinasi(id_wisata,harga_tiket,lokasi,jam_operasional) VALUES (?,?,?,?)";

        PreparedStatement ps2 =
                conn.prepareStatement(sql2);

        ps2.setString(1, id);
        ps2.setInt(2, harga);
        ps2.setString(3, lokasi);
        ps2.setString(4, jam);

        ps2.executeUpdate();

        conn.commit();

        System.out.println("Destinasi berhasil ditambahkan.");

    } catch (Exception e) {

        try {
            conn.rollback();
        } catch (Exception ex) {
        }

        System.out.println(e.getMessage());
    }

}
    public void editDestinasi() {

    try {

        System.out.print("\nMasukkan ID Wisata : ");
        String id = input.nextLine();

        String cek =
                "SELECT * FROM destinasi_wisata WHERE id_wisata=?";

        PreparedStatement ps =
                conn.prepareStatement(cek);

        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();

        if (!rs.next()) {

            System.out.println("ID tidak ditemukan.");
            return;

        }

        System.out.print("Nama Baru           : ");
        String nama = input.nextLine();

        System.out.print("Harga Baru          : ");
        int harga = input.nextInt();
        input.nextLine();

        System.out.print("Lokasi Baru         : ");
        String lokasi = input.nextLine();

        System.out.print("Jam Operasional     : ");
        String jam = input.nextLine();

        conn.setAutoCommit(false);

        String sql1 =
                "UPDATE destinasi_wisata SET nama_wisata=? WHERE id_wisata=?";

        PreparedStatement ps1 =
                conn.prepareStatement(sql1);

        ps1.setString(1, nama);
        ps1.setString(2, id);

        ps1.executeUpdate();

        String sql2 =
                "UPDATE detail_destinasi SET harga_tiket=?, lokasi=?, jam_operasional=? WHERE id_wisata=?";

        PreparedStatement ps2 =
                conn.prepareStatement(sql2);

        ps2.setInt(1, harga);
        ps2.setString(2, lokasi);
        ps2.setString(3, jam);
        ps2.setString(4, id);

        ps2.executeUpdate();

        conn.commit();

        System.out.println("Data berhasil diubah.");

    } catch (Exception e) {

        try {
            conn.rollback();
        } catch (Exception ex) {
        }

        System.out.println(e.getMessage());
    }

}
    public void hapusDestinasi() {

    try {

        System.out.print("\nMasukkan ID Wisata : ");
        String id = input.nextLine();

        conn.setAutoCommit(false);

        String sql1 =
                "DELETE FROM detail_destinasi WHERE id_wisata=?";

        PreparedStatement ps1 =
                conn.prepareStatement(sql1);

        ps1.setString(1, id);

        ps1.executeUpdate();

        String sql2 =
                "DELETE FROM destinasi_wisata WHERE id_wisata=?";

        PreparedStatement ps2 =
                conn.prepareStatement(sql2);

        ps2.setString(1, id);

        int hasil = ps2.executeUpdate();

        conn.commit();

        if (hasil > 0) {

            System.out.println("Destinasi berhasil dihapus.");

        } else {

            System.out.println("ID tidak ditemukan.");

        }

    } catch (Exception e) {

        try {
            conn.rollback();
        } catch (Exception ex) {
        }

        System.out.println(e.getMessage());
    }

}
    public List<destinasi_wisata> getAllDestinasi() {

    List<destinasi_wisata> daftar =
            new ArrayList<>();

    String sql =
        "SELECT dw.id_wisata, " +
        "dw.nama_wisata, " +
        "dd.harga_tiket, " +
        "dd.lokasi, " +
        "dd.jam_operasional " +
        "FROM destinasi_wisata dw " +
        "JOIN detail_destinasi dd " +
        "ON dw.id_wisata = dd.id_wisata";

    try {

        PreparedStatement ps =
                conn.prepareStatement(sql);

        ResultSet rs =
                ps.executeQuery();

        while(rs.next()){

            detail_destinasi_wisata detail =
                new detail_destinasi_wisata(

                    rs.getInt("harga_tiket"),
                    rs.getString("lokasi"),
                    rs.getString("jam_operasional")
                );

            destinasi_wisata wisata =
                new destinasi_wisata(

                    rs.getString("id_wisata"),
                    rs.getString("nama_wisata"),
                    detail
                );

            daftar.add(wisata);
        }

    } catch(Exception e){

        e.printStackTrace();
    }

    return daftar;
}
}