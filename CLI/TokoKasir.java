import java.util.*;
import java.io.*;

public class TokoKasir {
    static Scanner input = new Scanner(System.in);
    static List<Barang> daftarBarang = new ArrayList<>();
    static List<Transaksi> laporan = new ArrayList<>();

    public static void main(String[] args) {
        // Load data saat startup
        loadData();
        
        while (true) {
            System.out.println("========================");
            System.out.println("SISTEM KASIR TOKO");
            System.out.println("========================");
            System.out.println("1. Tambah Barang");
            System.out.println("2. Lihat Barang");
            System.out.println("3. Edit Barang");
            System.out.println("4. Hapus Barang");
            System.out.println("5. Transaksi Penjualan");
            System.out.println("6. Laporan Penjualan");
            System.out.println("7. Simpan Data");
            System.out.println("8. Keluar");
            System.out.print("Pilih menu: ");
            int pilih = input.nextInt();

            switch (pilih) {
                case 1: tambahBarang(); break;
                case 2: lihatBarang(); break;
                case 3: editBarang(); break;
                case 4: hapusBarang(); break;
                case 5: transaksiPenjualan(); break;
                case 6: laporanPenjualan(); break;
                case 7: simpanData(); break;
                case 8: 
                    simpanData(); // Auto save sebelum keluar
                    System.exit(0);
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

        // Cek apakah barang sudah ada berdasarkan nama
        Barang barangExist = cariBarangByNama(nama);
        if (barangExist != null) {
            System.out.println("Barang '" + nama + "' sudah ada dalam sistem!");
            System.out.println("Stok saat ini: " + barangExist.getStok());
            System.out.print("Tambah stok sebanyak " + stok + "? (y/n): ");
            input.nextLine(); // consume newline
            String konfirmasi = input.nextLine();
            if (konfirmasi.equalsIgnoreCase("y")) {
                barangExist.tambahStok(stok);
                System.out.println("Stok berhasil ditambahkan! Stok baru: " + barangExist.getStok());
            } else {
                System.out.println("Penambahan stok dibatalkan.");
            }
        } else {
            // Barang baru, tambahkan ke daftar
            if (jenis == 1) {
                daftarBarang.add(new BarangSembako(nama, harga, stok));
            } else if (jenis == 2) {
                daftarBarang.add(new BarangElektronik(nama, harga, stok));
            }
            System.out.println("Barang baru berhasil ditambahkan!");
        }
    }

    static void lihatBarang() {
        if (daftarBarang.isEmpty()) {
            System.out.println("Tidak ada barang dalam daftar!");
            return;
        }
        
        System.out.println("+----+--------------+--------+-------+--------------------------+");
        System.out.println("| ID | Nama         | Harga  | Stok  | Keterangan               |");
        System.out.println("+----+--------------+--------+-------+--------------------------+");
        for (Barang b : daftarBarang) {
            b.tampilkan();
        }
        System.out.println("+----+--------------+--------+-------+--------------------------+");
    }

    static void editBarang() {
        if (daftarBarang.isEmpty()) {
            System.out.println("Tidak ada barang untuk diedit!");
            return;
        }
        
        lihatBarang();
        System.out.print("Masukkan ID barang yang akan diedit: ");
        int id = input.nextInt();
        
        Barang barang = cariBarang(id);
        if (barang == null) {
            System.out.println("Barang tidak ditemukan!");
            return;
        }

        input.nextLine(); // consume newline
        System.out.print("Nama barang baru (kosongkan jika tidak diubah): ");
        String namaBaru = input.nextLine();
        if (!namaBaru.isEmpty()) {
            barang.setNama(namaBaru);
        }

        System.out.print("Harga baru (0 jika tidak diubah): ");
        int hargaBaru = input.nextInt();
        if (hargaBaru > 0) {
            barang.setHarga(hargaBaru);
        }

        System.out.print("Stok baru (-1 jika tidak diubah): ");
        int stokBaru = input.nextInt();
        if (stokBaru >= 0) {
            barang.setStok(stokBaru);
        }

        System.out.println("Barang berhasil diupdate!");
    }

    static void hapusBarang() {
        if (daftarBarang.isEmpty()) {
            System.out.println("Tidak ada barang untuk dihapus!");
            return;
        }
        
        lihatBarang();
        System.out.print("Masukkan ID barang yang akan dihapus: ");
        int id = input.nextInt();
        
        Barang barang = cariBarang(id);
        if (barang == null) {
            System.out.println("Barang tidak ditemukan!");
            return;
        }

        System.out.print("Konfirmasi hapus barang '" + barang.getNama() + "'? (y/n): ");
        input.nextLine(); // consume newline
        String konfirmasi = input.nextLine();
        if (konfirmasi.equalsIgnoreCase("y")) {
            daftarBarang.remove(barang);
            System.out.println("Barang berhasil dihapus!");
        } else {
            System.out.println("Penghapusan dibatalkan.");
        }
    }

    static void transaksiPenjualan() {
        if (daftarBarang.isEmpty()) {
            System.out.println("Tidak ada barang untuk dijual!");
            return;
        }

        Transaksi transaksi = new Transaksi();
        boolean lanjut = true;

        while (lanjut) {
            lihatBarang();
            System.out.print("Masukkan ID barang: ");
            int id = input.nextInt();
            
            Barang barang = cariBarang(id);
            if (barang == null) {
                System.out.println("Barang tidak ditemukan!");
                continue;
            }

            System.out.print("Masukkan jumlah: ");
            int jumlah = input.nextInt();

            // Validasi stok
            if (jumlah > barang.getStok()) {
                System.out.println("Stok tidak mencukupi! Stok tersedia: " + barang.getStok());
                continue;
            }

            if (barang.getStok() == 0) {
                System.out.println("Stok barang sudah habis! Penjualan gagal.");
                continue;
            }

            int subtotal = barang.getHarga() * jumlah;
            System.out.println("Nama Barang : " + barang.getNama());
            System.out.println("Harga Satuan : Rp " + barang.getHarga());
            System.out.println("Jumlah : " + jumlah);
            System.out.println("Subtotal : Rp " + subtotal);

            System.out.print("Tambah item ini ke transaksi? (y/n): ");
            input.nextLine(); // consume newline
            String konfirmasi = input.nextLine();
            if (konfirmasi.equalsIgnoreCase("y")) {
                transaksi.tambahItem(new TransaksiItem(barang, jumlah));
                System.out.println("Item berhasil ditambahkan ke transaksi!");
            }

            System.out.print("Tambah item lain? (y/n): ");
            String tambahLagi = input.nextLine();
            if (!tambahLagi.equalsIgnoreCase("y")) {
                lanjut = false;
            }
        }

        if (!transaksi.getItems().isEmpty()) {
            System.out.println("\n=== RINGKASAN TRANSAKSI ===");
            transaksi.tampilkanTransaksi();
            
            System.out.print("Konfirmasi transaksi? (y/n): ");
            String konfirmasiFinal = input.nextLine();
            if (konfirmasiFinal.equalsIgnoreCase("y")) {
                laporan.add(transaksi);
                System.out.println("Transaksi berhasil disimpan!");
            } else {
                // Rollback stok jika transaksi dibatalkan
                for (TransaksiItem item : transaksi.getItems()) {
                    item.getBarang().tambahStok(item.getJumlah());
                }
                System.out.println("Transaksi dibatalkan.");
            }
        } else {
            System.out.println("Tidak ada item dalam transaksi!");
        }
    }

    static Barang cariBarang(int id) {
        for (Barang b : daftarBarang) {
            if (b.getId() == id) return b;
        }
        return null;
    }

    static Barang cariBarangByNama(String nama) {
        for (Barang b : daftarBarang) {
            if (b.getNama().equalsIgnoreCase(nama)) {
                return b;
            }
        }
        return null;
    }

    static void laporanPenjualan() {
        if (laporan.isEmpty()) {
            System.out.println("Belum ada transaksi!");
            return;
        }
        
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

    static void simpanData() {
        try {
            // Simpan data barang
            PrintWriter writer = new PrintWriter("data_barang.txt");
            for (Barang barang : daftarBarang) {
                String jenis = "";
                if (barang instanceof BarangSembako) {
                    jenis = "Sembako";
                } else if (barang instanceof BarangElektronik) {
                    jenis = "Elektronik";
                }
                writer.println(barang.getId() + "," + jenis + "," + barang.getNama() + "," + 
                             barang.getHarga() + "," + barang.getStok());
            }
            writer.close();

            // Simpan data transaksi
            PrintWriter writerTransaksi = new PrintWriter("data_transaksi.txt");
            for (Transaksi transaksi : laporan) {
                for (TransaksiItem item : transaksi.getItems()) {
                    writerTransaksi.println(item.getBarang().getId() + "," + 
                                         item.getJumlah() + "," + item.getSubtotal());
                }
            }
            writerTransaksi.close();

            System.out.println("Data berhasil disimpan ke file!");
        } catch (IOException e) {
            System.out.println("Error saat menyimpan data: " + e.getMessage());
        }
    }

    static void loadData() {
        try {
            // Load data barang
            File fileBarang = new File("data_barang.txt");
            if (fileBarang.exists()) {
                Scanner fileScanner = new Scanner(fileBarang);
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    String[] parts = line.split(",");
                    if (parts.length >= 5) {
                        int id = Integer.parseInt(parts[0]);
                        String jenis = parts[1];
                        String nama = parts[2];
                        int harga = Integer.parseInt(parts[3]);
                        int stok = Integer.parseInt(parts[4]);

                        if (jenis.equals("Sembako")) {
                            daftarBarang.add(new BarangSembako(nama, harga, stok));
                        } else if (jenis.equals("Elektronik")) {
                            daftarBarang.add(new BarangElektronik(nama, harga, stok));
                        }
                    }
                }
                fileScanner.close();
                System.out.println("Data barang berhasil dimuat dari file.");
            }
        } catch (IOException e) {
            System.out.println("Error saat memuat data: " + e.getMessage());
        }
    }
}
