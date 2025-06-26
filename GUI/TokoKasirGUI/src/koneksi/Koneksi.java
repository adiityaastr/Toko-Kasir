package koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/toko_kasir";
        String user = "root";
        String pass = ""; // ganti sesuai password MySQL kamu
        return DriverManager.getConnection(url, user, pass);
    }

    public static void main(String[] args) {
        try {
            Connection conn = getConnection();
            if (conn != null) {
                System.out.println("Koneksi ke database BERHASIL!");
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Koneksi ke database GAGAL: " + e.getMessage());
        }
    }
}