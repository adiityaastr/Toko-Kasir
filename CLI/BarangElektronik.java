public class BarangElektronik extends Barang {
    public BarangElektronik(String nama, int harga, int stok) {
        super(nama, harga, stok);
    }

    @Override
    public void tampilkan() {
        System.out.printf("| %-2d | %-12s | %-6d | %-5d | Jenis: Elektronik\n",
            getId(), getNama(), getHarga(), getStok());
    }
}
