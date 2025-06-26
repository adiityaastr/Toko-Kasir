public class BarangSembako extends Barang {
    public BarangSembako(String nama, int harga, int stok) {
        super(nama, harga, stok);
    }

    @Override
    public void tampilkan() {
        System.out.printf("| %-2d | %-12s | %-6d | %-5d | Jenis: Sembako\n",
            getId(), getNama(), getHarga(), getStok());
    }
}
