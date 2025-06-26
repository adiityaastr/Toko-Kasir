public abstract class Barang {
    private static int counter = 1;
    private int id;
    private String nama;
    private int harga;
    private int stok;

    public Barang(String nama, int harga, int stok) {
        this.id = counter++;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    public int getId() { return id; }
    public String getNama() { return nama; }
    public int getHarga() { return harga; }
    public int getStok() { return stok; }

    public void kurangiStok(int jumlah) {
        this.stok -= jumlah;
    }

    // Abstract method → harus diimplementasikan oleh anaknya (abstraksi)
    public abstract void tampilkan();
}
