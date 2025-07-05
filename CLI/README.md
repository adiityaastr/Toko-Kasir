# SISTEM KASIR TOKO - DOKUMENTASI

## Fitur Utama

### 1. CRUD (Create, Read, Update, Delete) Barang
- **Create**: Tambah barang baru (Sembako atau Elektronik)
- **Read**: Lihat daftar semua barang dengan format tabel
- **Update**: Edit informasi barang (nama, harga, stok)
- **Delete**: Hapus barang dari sistem dengan konfirmasi
- **Auto-Update Stok**: Jika menambah barang dengan nama yang sama, sistem akan mengupdate stok barang yang sudah ada

### 2. Transaksi Multi-Item
- Dapat menambahkan multiple item dalam satu transaksi
- Validasi stok real-time
- Konfirmasi setiap item sebelum ditambahkan
- Rollback stok jika transaksi dibatalkan
- Ringkasan transaksi sebelum konfirmasi final

### 3. Validasi Stok
- Pengecekan stok sebelum transaksi
- Pesan error jika stok tidak mencukupi
- Pesan error jika stok sudah habis (0)
- Mencegah transaksi dengan stok 0
- Validasi input untuk memastikan data yang dimasukkan valid

### 4. Sistem Penyimpanan Data
- Auto-save saat keluar dari program
- Manual save melalui menu
- Data tersimpan dalam file:
  - `data_barang.txt`: Data barang
  - `data_transaksi.txt`: Data transaksi
- Auto-load data saat startup
- Error handling untuk operasi file I/O

## Struktur Class

### Barang (Abstract Class)
- `setNama(String)`: Set nama barang
- `setHarga(int)`: Set harga barang  
- `setStok(int)`: Set stok barang
- `kurangiStok(int)`: Kurangi stok
- `tambahStok(int)`: Tambah stok (untuk rollback dan update)

### Transaksi
- `getItems()`: Ambil daftar item transaksi
- `tambahItem(TransaksiItem)`: Tambah item ke transaksi
- `tampilkanTransaksi()`: Tampilkan ringkasan transaksi

### TokoKasir (Main Class)
- `tambahBarang()`: Tambah barang baru atau update stok barang yang ada
- `lihatBarang()`: Tampilkan daftar semua barang
- `editBarang()`: Edit informasi barang
- `hapusBarang()`: Hapus barang dengan konfirmasi
- `transaksiPenjualan()`: Transaksi multi-item dengan validasi
- `laporanPenjualan()`: Tampilkan laporan transaksi
- `simpanData()`: Simpan data ke file
- `loadData()`: Load data dari file
- `cariBarang(int)`: Cari barang berdasarkan ID
- `cariBarangByNama(String)`: Cari barang berdasarkan nama (case-insensitive)

## Alur Tambah Barang (Dengan Auto-Update)

1. Pilih menu "Tambah Barang"
2. Pilih jenis barang (Sembako/Elektronik)
3. Masukkan nama barang
4. Masukkan harga dan stok
5. Sistem cek apakah barang sudah ada:
   - **Jika sudah ada**: Tampilkan stok saat ini dan tanya konfirmasi untuk menambah stok
   - **Jika belum ada**: Buat barang baru dengan ID baru

## Alur Transaksi Multi-Item

1. Pilih menu "Transaksi Penjualan"
2. Lihat daftar barang yang tersedia
3. Masukkan ID barang yang ingin dibeli
4. Masukkan jumlah yang diinginkan
5. Sistem validasi stok:
   - Jika stok = 0: "Stok barang sudah habis! Penjualan gagal."
   - Jika jumlah > stok: "Stok tidak mencukupi! Stok tersedia: X"
6. Konfirmasi tambah item ke transaksi
7. Pilih apakah ingin menambah item lain
8. Tampilkan ringkasan transaksi
9. Konfirmasi final transaksi
10. Jika dibatalkan: rollback stok semua item

## File Output

### data_barang.txt
Format: `ID,Jenis,Nama,Harga,Stok`
Contoh:
```
1,Sembako,Beras,12000,50
2,Elektronik,Laptop,8000000,5
```

### data_transaksi.txt  
Format: `ID_Barang,Jumlah,Subtotal`
Contoh:
```
1,2,24000
2,1,8000000
```

## Keunggulan Sistem

1. **Data Persistence**: Data tidak hilang saat program ditutup
2. **Validasi Real-time**: Stok dicek setiap transaksi
3. **Multi-item Support**: Satu transaksi bisa berisi banyak item
4. **Rollback Mechanism**: Stok dikembalikan jika transaksi dibatalkan
5. **User-friendly**: Konfirmasi di setiap langkah penting
6. **Error Handling**: Pesan error yang informatif
7. **Auto-Update Stok**: Mencegah duplikasi barang dengan nama sama
8. **Case-Insensitive Search**: Pencarian barang tidak sensitif huruf besar/kecil
9. **Input Validation**: Validasi input untuk mencegah error
10. **File I/O Error Handling**: Penanganan error saat operasi file

## Struktur File Proyek

```
CLI/
├── README.md              ← Dokumentasi utama
├── DEMO_AUTO_UPDATE.md    ← Demonstrasi fitur auto-update
├── TokoKasir.java         ← Program utama
├── Barang.java            ← Class abstrak barang
├── BarangSembako.java     ← Class turunan sembako
├── BarangElektronik.java  ← Class turunan elektronik
├── Transaksi.java         ← Class transaksi
├── TransaksiItem.java     ← Class item transaksi
├── data_barang.txt        ← File data barang (auto-generated)
├── data_transaksi.txt     ← File data transaksi (auto-generated)
└── *.class                ← File bytecode Java (auto-generated)
```

## Cara Menjalankan

```bash
javac *.java
java TokoKasir
```

## Menu Sistem

1. **Tambah Barang**: Tambah barang baru atau update stok barang yang ada
2. **Lihat Barang**: Tampilkan semua barang
3. **Edit Barang**: Edit informasi barang
4. **Hapus Barang**: Hapus barang dari sistem
5. **Transaksi Penjualan**: Lakukan transaksi multi-item
6. **Laporan Penjualan**: Lihat laporan transaksi
7. **Simpan Data**: Simpan data ke file
8. **Keluar**: Keluar dengan auto-save 