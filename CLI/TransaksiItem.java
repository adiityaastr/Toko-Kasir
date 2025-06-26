public class TransaksiItem {
    private Barang barang;
    private int jumlah;

    public TransaksiItem(Barang barang, int jumlah) {
        this.barang = barang;
        this.jumlah = jumlah;
    }

    public Barang getBarang() { return barang; }
    public int getJumlah() { return jumlah; }
    public int getSubtotal() { return barang.getHarga() * jumlah; }

    public void tampilkan() {
        System.out.println("- " + barang.getNama() + ": " + jumlah + " pcs");
    }
}
