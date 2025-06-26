import java.util.*;

public class TokoKasir {
    static Scanner input = new Scanner(System.in);
    static List<Barang> daftarBarang = new ArrayList<>();
    static List<Transaksi> laporan = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("========================");
            System.out.println("SISTEM KASIR TOKO");
            System.out.println("========================");
            System.out.println("1. Tambah Barang");
            System.out.println("2. Lihat Barang");
            System.out.println("3. Transaksi Penjualan");
            System.out.println("4. Laporan Penjualan");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            int pilih = input.nextInt();

            switch (pilih) {
                case 1: tambahBarang(); break;
                case 2: lihatBarang(); break;
                case 3: transaksiPenjualan(); break;
                case 4: laporanPenjualan(); break;
                case 5: System.exit(0);
            }
        }
    }

    static void tambahBarang() {
    System.out.println("Jenis barang:");
    System.out.println("1. Sembako");
    System.out.println("2. Elektronik");
    System.out.print("Pilih jenis: ");
    int jenis = input.nextInt();

    input.nextLine(); // consume newline
    System.out.print("Nama barang: ");
    String nama = input.nextLine();
    System.out.print("Harga: ");
    int harga = input.nextInt();
    System.out.print("Stok: ");
    int stok = input.nextInt();

    if (jenis == 1) {
        daftarBarang.add(new BarangSembako(nama, harga, stok));
    } else if (jenis == 2) {
        daftarBarang.add(new BarangElektronik(nama, harga, stok)); // tanpa garansi
    }

    System.out.println("Barang berhasil ditambahkan!");
}

    static void lihatBarang() {
        System.out.println("+----+--------------+--------+-------+--------------------------+");
        System.out.println("| ID | Nama         | Harga  | Stok  | Keterangan               |");
        System.out.println("+----+--------------+--------+-------+--------------------------+");
        for (Barang b : daftarBarang) {
            b.tampilkan(); // Polimorfisme: tampilkan sesuai jenisnya
        }
        System.out.println("+----+--------------+--------+-------+--------------------------+");
    }

    static void transaksiPenjualan() {
        System.out.print("Masukkan ID barang: ");
        int id = input.nextInt();
        Barang barang = cariBarang(id);
        if (barang == null) {
            System.out.println("Barang tidak ditemukan!");
            return;
        }

        System.out.print("Masukkan jumlah: ");
        int jumlah = input.nextInt();
        int subtotal = barang.getHarga() * jumlah;

        System.out.println("Nama Barang : " + barang.getNama());
        System.out.println("Harga Satuan : Rp " + barang.getHarga());
        System.out.println("Subtotal : Rp " + subtotal);

        System.out.print("Konfirmasi transaksi? (y/n): ");
        input.nextLine(); // consume newline
        String konfirmasi = input.nextLine();
        if (konfirmasi.equalsIgnoreCase("y")) {
            Transaksi transaksi = new Transaksi();
            transaksi.tambahItem(new TransaksiItem(barang, jumlah));
            laporan.add(transaksi);
            System.out.println("Stok berhasil dikurangi.");
            System.out.println("Transaksi berhasil disimpan!");
        } else {
            System.out.println("Transaksi dibatalkan.");
        }
    }

    static Barang cariBarang(int id) {
        for (Barang b : daftarBarang) {
            if (b.getId() == id) return b;
        }
        return null;
    }

    static void laporanPenjualan() {
        System.out.println("Laporan Transaksi Penjualan");
        int totalHarian = 0;
        int no = 1;
        for (Transaksi t : laporan) {
            System.out.println("Transaksi " + no + ":");
            t.tampilkanTransaksi();
            System.out.println();
            totalHarian += t.getTotal();
            no++;
        }
        System.out.println("---------------------------------");
        System.out.println("Total pendapatan hari ini: Rp " + totalHarian);
    }
}
