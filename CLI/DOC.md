# Dokumentasi Program Kasir Toko (OOP)

## Deskripsi Program
Program ini adalah aplikasi kasir sederhana berbasis CLI (Command Line Interface) untuk toko. Pengguna dapat menambah barang (sembako/elektronik), melihat daftar barang, melakukan transaksi penjualan, dan melihat laporan penjualan harian. Program ini dibuat menggunakan prinsip-prinsip OOP (Object Oriented Programming) di Java.

## Penjelasan Kelas
- **Barang (abstract)**: Kelas dasar untuk semua barang di toko. Menyimpan data umum (id, nama, harga, stok), menyediakan method getter, method abstrak `tampilkan()`, dan method untuk mengurangi stok.
- **BarangSembako**: Turunan dari `Barang` untuk barang kebutuhan pokok. Mengimplementasikan method `tampilkan()` untuk menampilkan info barang sembako.
- **BarangElektronik**: Turunan dari `Barang` untuk barang elektronik. Mengimplementasikan method `tampilkan()` untuk menampilkan info barang elektronik.
- **TransaksiItem**: Mewakili satu item dalam transaksi penjualan. Menyimpan referensi ke objek `Barang` dan jumlah yang dibeli, serta method untuk menghitung subtotal.
- **Transaksi**: Menyimpan daftar item yang dibeli dalam satu transaksi (list of `TransaksiItem`), serta total harga. Ada method untuk menambah item dan menampilkan transaksi.
- **TokoKasir**: Kelas utama (main) yang menjalankan menu, mengelola daftar barang, transaksi, dan laporan. Menjadi penghubung antara user dan seluruh proses bisnis aplikasi.

## Contoh Output Program
```
========================
SISTEM KASIR TOKO
========================
1. Tambah Barang
2. Lihat Barang
3. Transaksi Penjualan
4. Laporan Penjualan
5. Keluar
Pilih menu: 1
Jenis barang:
1. Sembako
2. Elektronik
Pilih jenis: 1
Nama barang: Beras
Harga: 12000
Stok: 50
Barang berhasil ditambahkan!

Pilih menu: 2
+----+--------------+--------+-------+--------------------------+
| ID | Nama         | Harga  | Stok  | Keterangan               |
+----+--------------+--------+-------+--------------------------+
| 1  | Beras        | 12000  | 50    | Jenis: Sembako
+----+--------------+--------+-------+--------------------------+

Pilih menu: 3
Masukkan ID barang: 1
Masukkan jumlah: 2
Nama Barang : Beras
Harga Satuan : Rp 12000
Subtotal : Rp 24000
Konfirmasi transaksi? (y/n): y
Stok berhasil dikurangi.
Transaksi berhasil disimpan!

Pilih menu: 4
Laporan Transaksi Penjualan
Transaksi 1:
- Beras: 2 pcs
Total: Rp 24000
---------------------------------
Total pendapatan hari ini: Rp 24000
```

## Penerapan 4 Pilar OOP (Object Oriented Programming)

### 1. Enkapsulasi (Encapsulation)
**Penjelasan:**
Enkapsulasi adalah konsep membungkus data (atribut) dan perilaku (method) dalam satu unit (kelas), serta membatasi akses langsung ke data dengan menggunakan modifier akses (private, public, dll). Data hanya bisa diakses atau diubah melalui method (getter/setter).

**Penerapan di Program:**
- Semua atribut pada kelas seperti `Barang`, `TransaksiItem`, dan `Transaksi` dibuat private.
- Akses ke atribut dilakukan melalui method getter/setter atau method publik lain.
- Contoh pada `Barang.java`:
  ```java
  private int harga;
  public int getHarga() { return harga; }
  ```

### 2. Pewarisan (Inheritance)
**Penjelasan:**
Pewarisan adalah konsep di mana sebuah kelas dapat mewarisi atribut dan method dari kelas lain. Kelas yang mewarisi disebut subclass (anak), sedangkan kelas yang diwarisi disebut superclass (induk).

**Penerapan di Program:**
- `BarangSembako` dan `BarangElektronik` mewarisi dari kelas abstrak `Barang`.
- Kelas turunan dapat menggunakan/mengubah method dan atribut dari kelas induk.
- Contoh pada `BarangSembako.java`:
  ```java
  public class BarangSembako extends Barang {
      public BarangSembako(String nama, int harga, int stok) {
          super(nama, harga, stok);
      }
      // ...
  }
  ```

### 3. Polimorfisme (Polymorphism)
**Penjelasan:**
Polimorfisme adalah kemampuan objek untuk memiliki banyak bentuk, terutama saat method yang sama di kelas induk di-override oleh kelas turunan. Pemanggilan method pada referensi kelas induk akan menjalankan implementasi sesuai objek aslinya.

**Penerapan di Program:**
- Method `tampilkan()` di-override pada `BarangSembako` dan `BarangElektronik`.
- Saat menampilkan daftar barang, method `tampilkan()` yang dipanggil sesuai jenis objeknya.
- Contoh pada `TokoKasir.java`:
  ```java
  for (Barang b : daftarBarang) {
      b.tampilkan(); // Polimorfisme: tampilkan sesuai jenis barang
  }
  ```

### 4. Abstraksi (Abstraction)
**Penjelasan:**
Abstraksi adalah konsep menyembunyikan detail implementasi dan hanya menampilkan fungsionalitas penting. Biasanya menggunakan kelas abstrak atau interface.

**Penerapan di Program:**
- Kelas `Barang` dideklarasikan sebagai abstract dan memiliki method abstrak `tampilkan()`.
- Kelas turunan wajib mengimplementasikan method abstrak tersebut.
- Contoh pada `Barang.java`:
  ```java
  public abstract class Barang {
      // ...
      public abstract void tampilkan();
  }
  ``` 