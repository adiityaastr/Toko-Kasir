# DEMONSTRASI FITUR AUTO-UPDATE STOK

## Contoh Skenario Penggunaan

### Skenario 1: Menambah Barang Baru
```
Pilih menu: 1
Jenis barang:
1. Sembako
2. Elektronik
Pilih jenis: 1
Nama barang: Beras
Harga: 12000
Stok: 50
Barang baru berhasil ditambahkan!
```

### Skenario 2: Menambah Barang yang Sudah Ada
```
Pilih menu: 1
Jenis barang:
1. Sembako
2. Elektronik
Pilih jenis: 1
Nama barang: Beras
Harga: 12000
Stok: 30
Barang 'Beras' sudah ada dalam sistem!
Stok saat ini: 50
Tambah stok sebanyak 30? (y/n): y
Stok berhasil ditambahkan! Stok baru: 80
```

### Skenario 3: Menambah Barang dengan Nama yang Sama (Case-Insensitive)
```
Pilih menu: 1
Jenis barang:
1. Sembako
2. Elektronik
Pilih jenis: 1
Nama barang: BERAS
Harga: 12000
Stok: 20
Barang 'BERAS' sudah ada dalam sistem!
Stok saat ini: 80
Tambah stok sebanyak 20? (y/n): y
Stok berhasil ditambahkan! Stok baru: 100
```

## Keunggulan Fitur Auto-Update

### 1. Mencegah Duplikasi
- Tidak ada lagi barang dengan nama sama yang terduplikasi
- Sistem otomatis mendeteksi barang yang sudah ada

### 2. Case-Insensitive Search
- Pencarian tidak sensitif huruf besar/kecil
- "Beras", "BERAS", "beras" dianggap sama

### 3. Konfirmasi User
- User diberi informasi stok saat ini
- User dapat memilih untuk menambah stok atau membatalkan

### 4. Pesan yang Informatif
- Menampilkan nama barang yang sudah ada
- Menampilkan stok saat ini
- Menampilkan jumlah stok yang akan ditambahkan
- Menampilkan stok baru setelah penambahan

## Alur Logika

```java
// Pseudo-code alur auto-update
1. User input nama barang
2. Sistem cari barang dengan nama yang sama (case-insensitive)
3. Jika ditemukan:
   - Tampilkan pesan barang sudah ada
   - Tampilkan stok saat ini
   - Tanya konfirmasi untuk menambah stok
   - Jika ya: tambah stok dan tampilkan stok baru
   - Jika tidak: batalkan operasi
4. Jika tidak ditemukan:
   - Buat barang baru dengan ID baru
   - Tambahkan ke daftar barang
```

## Perbandingan Sebelum dan Sesudah

### SEBELUM (Sistem Lama)
```
Barang 1: Beras, Stok: 50
Barang 2: Beras, Stok: 30  ← Duplikasi!
Barang 3: BERAS, Stok: 20  ← Duplikasi lagi!
```

### SESUDAH (Sistem Baru)
```
Barang 1: Beras, Stok: 100  ← Stok digabung otomatis
```

## Method yang Ditambahkan

```java
static Barang cariBarangByNama(String nama) {
    for (Barang b : daftarBarang) {
        if (b.getNama().equalsIgnoreCase(nama)) {
            return b;
        }
    }
    return null;
}
```

## Integrasi dengan Fitur Lain

- **Edit Barang**: Tetap menggunakan ID untuk edit
- **Hapus Barang**: Tetap menggunakan ID untuk hapus
- **Transaksi**: Tetap menggunakan ID untuk transaksi
- **Auto-Update**: Hanya untuk penambahan barang baru

Fitur auto-update ini membuat sistem lebih user-friendly dan mencegah kebingungan akibat duplikasi barang. 