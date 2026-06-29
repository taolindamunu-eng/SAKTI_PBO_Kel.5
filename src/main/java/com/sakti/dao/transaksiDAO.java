package com.sakti.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.sakti.config.DatabaseConnection;

public class transaksiDAO {
    
    private Connection conn;
    public transaksiDAO() {
        conn = DatabaseConnection.getConnection();
    }

public void lihatRekapTransaksi() {

    String sql =
        "SELECT t.id_transaksi, " +
        "u.nama, " +
        "d.nama_wisata, " +
        "t.jumlah_tiket, " +
        "t.total_bayar, " +
        "t.status, " +
        "t.tanggal " +
        "FROM transaksi t " +
        "JOIN users u ON t.id_user = u.id_user " +
        "JOIN destinasi_wisata d ON t.id_wisata = d.id_wisata " +
        "ORDER BY t.tanggal DESC";

    try {

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        System.out.println("\n==================================== REKAP TRANSAKSI ====================================");

        System.out.printf("%-12s %-15s %-25s %-8s %-15s %-10s%n",
                "ID",
                "Nama",
                "Wisata",
                "Tiket",
                "Total",
                "Status");

        System.out.println("-----------------------------------------------------------------------------------------");

        boolean adaData = false;

        while (rs.next()) {

            adaData = true;

            System.out.printf("%-12s %-15s %-25s %-8d Rp%-13d %-10s%n",
                    rs.getString("id_transaksi"),
                    rs.getString("nama"),
                    rs.getString("nama_wisata"),
                    rs.getInt("jumlah_tiket"),
                    rs.getInt("total_bayar"),
                    rs.getString("status"));

        }

        if (!adaData) {
            System.out.println("Belum ada transaksi.");
        }

        System.out.println("=========================================================================================");

    } catch (Exception e) {

        System.out.println("Gagal menampilkan rekap transaksi.");
        System.out.println(e.getMessage());

    }


}
public void simpanTransaksi(String id_transaksi, int id_user,
                            String id_wisata,
                            int jumlah_tiket,
                            int total_bayar,
                            String status) {

    String sql = "INSERT INTO transaksi (id_transaksi, id_user, id_wisata, jumlah_tiket, total_bayar, status) VALUES (?, ?, ?, ?, ?, ?)";

    try {

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, id_transaksi);
        ps.setInt(2, id_user);
        ps.setString(3, id_wisata);
        ps.setInt(4, jumlah_tiket);
        ps.setInt(5, total_bayar);
        ps.setString(6, "BERHASIL");

        ps.executeUpdate();

    } catch (Exception e) {
        System.out.println("Gagal simpan transaksi: " + e.getMessage());
    }
}

public ResultSet getETicket(String idTransaksi){

    String sql =
        "SELECT t.id_transaksi, " +
        "u.nama, " +
        "d.nama_wisata, " +
        "dd.harga_tiket, " +
        "t.jumlah_tiket, " +
        "t.total_bayar, " +
        "t.status, " +
        "t.tanggal " +
        "FROM transaksi t " +
        "JOIN users u ON t.id_user=u.id_user " +
        "JOIN destinasi_wisata d ON t.id_wisata=d.id_wisata " +
        "JOIN detail_destinasi dd ON d.id_wisata=dd.id_wisata " +
        "WHERE t.id_transaksi=?";

    try{

        PreparedStatement ps =
                conn.prepareStatement(sql);

        ps.setString(1,idTransaksi);

        return ps.executeQuery();

    }catch(Exception e){

        e.printStackTrace();
    }

    return null;
}

}