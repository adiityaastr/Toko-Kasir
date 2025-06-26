package model;

import java.util.Date;

public class Transaksi {
    private int id;
    private Date tanggal;
    private double totalHarga;
    private int jumlahItem;

    public Transaksi(int id, Date tanggal, double totalHarga, int jumlahItem) {
        this.id = id;
        this.tanggal = tanggal;
        this.totalHarga = totalHarga;
        this.jumlahItem = jumlahItem;
    }

    // Getter dan Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Date getTanggal() { return tanggal; }
    public void setTanggal(Date tanggal) { this.tanggal = tanggal; }
    public double getTotalHarga() { return totalHarga; }
    public void setTotalHarga(double totalHarga) { this.totalHarga = totalHarga; }
    public int getJumlahItem() { return jumlahItem; }
    public void setJumlahItem(int jumlahItem) { this.jumlahItem = jumlahItem; }
}