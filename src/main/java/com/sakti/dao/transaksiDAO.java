package com.sakti.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sakti.config.DatabaseConnection;
import com.sakti.model.TransaksiView;

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
        ps.setString(6, "MENUNGGU PEMBAYARAN");

        ps.executeUpdate();

    } catch (Exception e) {
        System.out.println("Gagal simpan transaksi: " + e.getMessage());
    }
}
public TransaksiView getTransaksiById(String idTransaksi) {

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
            "WHERE t.id_transaksi = ?";

    try {

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, idTransaksi);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            TransaksiView transaksi = new TransaksiView();

            transaksi.setIdTransaksi(rs.getString("id_transaksi"));
            transaksi.setNamaPengunjung(rs.getString("nama"));
            transaksi.setNamaWisata(rs.getString("nama_wisata"));
            transaksi.setJumlahTiket(rs.getInt("jumlah_tiket"));
            transaksi.setTotalBayar(rs.getInt("total_bayar"));
            transaksi.setStatus(rs.getString("status"));
            transaksi.setTanggal(rs.getTimestamp("tanggal"));

            return transaksi;
        }

    } catch (Exception e) {

        e.printStackTrace();

    }

    return null;
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

public java.util.List<com.sakti.model.TransaksiView> getAllTransaksi() {

    java.util.List<com.sakti.model.TransaksiView> daftar =
            new java.util.ArrayList<>();

    String sql =
            "SELECT t.id_transaksi, " +
            "u.nama, " +
            "d.nama_wisata, " +
            "t.jumlah_tiket, " +
            "t.total_bayar, " +
            "t.status, " +
            "t.tanggal " +
            "FROM transaksi t " +
            "JOIN users u ON t.id_user=u.id_user " +
            "JOIN destinasi_wisata d ON t.id_wisata=d.id_wisata " +
            "ORDER BY t.tanggal DESC";

    try {

        PreparedStatement ps =
                conn.prepareStatement(sql);

        ResultSet rs =
                ps.executeQuery();

        while(rs.next()){

            com.sakti.model.TransaksiView t =
                    new com.sakti.model.TransaksiView();

            t.setIdTransaksi(
                    rs.getString("id_transaksi"));

            t.setNamaPengunjung(
                    rs.getString("nama"));

            t.setNamaWisata(
                    rs.getString("nama_wisata"));

            t.setJumlahTiket(
                    rs.getInt("jumlah_tiket"));

            t.setTotalBayar(
                    rs.getInt("total_bayar"));

            t.setStatus(
                    rs.getString("status"));

            t.setTanggal(
                    rs.getTimestamp("tanggal"));

            daftar.add(t);
        }

    } catch (Exception e) {

        e.printStackTrace();

    }

    return daftar;
}

public String simpanTransaksiOtomatis(
        int idUser,
        String idWisata,
        int jumlahTiket) {

    try {

        String idTransaksi = "";

        String sqlId =
                "SELECT COUNT(*) + 1 AS nomor FROM transaksi";

        PreparedStatement psId =
                conn.prepareStatement(sqlId);

        ResultSet rs =
                psId.executeQuery();

        if (rs.next()) {

            idTransaksi =
                    String.format("TR%03d", rs.getInt("nomor"));

        }

        String sqlHarga =
                "SELECT harga_tiket FROM detail_destinasi WHERE id_wisata=?";

        PreparedStatement psHarga =
                conn.prepareStatement(sqlHarga);

        psHarga.setString(1, idWisata);

        ResultSet rsHarga =
                psHarga.executeQuery();

        int harga = 0;

        if (rsHarga.next()) {

            harga = rsHarga.getInt("harga_tiket");

        }

        int total = harga * jumlahTiket;

        String sql =
                "INSERT INTO transaksi(" +
                "id_transaksi," +
                "id_user," +
                "id_wisata," +
                "jumlah_tiket," +
                "total_bayar," +
                "status) " +
                "VALUES(?,?,?,?,?,?)";

        PreparedStatement ps =
                conn.prepareStatement(sql);

        ps.setString(1, idTransaksi);
        ps.setInt(2, idUser);
        ps.setString(3, idWisata);
        ps.setInt(4, jumlahTiket);
        ps.setInt(5, total);
        ps.setString(6, "MENUNGGU PEMBAYARAN");

        ps.executeUpdate();

        return idTransaksi;

    } catch (Exception e) {

        e.printStackTrace();

    }

    return null;
}
public boolean updateStatus(String idTransaksi, String status){

    String sql =
            "UPDATE transaksi SET status=? WHERE id_transaksi=?";

    try{

        PreparedStatement ps =
                conn.prepareStatement(sql);

        ps.setString(1, status);
        ps.setString(2, idTransaksi);

        return ps.executeUpdate() > 0;

    }catch(Exception e){

        e.printStackTrace();

    }

    return false;

}

}