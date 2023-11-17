# AseanCountry_UI

# Kriteria:
**Fitur yang harus ada pada aplikasi:**

  **1. Halaman List**
     
     Syarat:
      - Menampilkan data dalam format List dengan jumlah minimal 10 item yang berbeda. Gunakanlah LazyList/LazyGrid untuk menyusun datanya. 
      - Memunculkan halaman detail ketika salah satu item ditekan. 

  **2. Halaman Detail**
     
     Syarat:
      - Menampilkan gambar dan informasi yang relevan pada halaman detail. 
      - Informasi yang relevan mencakup kesamaan informasi yang ditampilkan pada halaman utama dengan halaman detail.
            - Terdapat judul dan gambar yang sesuai dengan list
            - Terdapat informasi tambahan yang tidak sama dengan list

**3. Halaman About**

     Syarat
      - Menampilkan foto diri, nama, dan email yang terdaftar di Dicoding.
      - Dalam mengakses halaman about, pastikan terdapat tombol yang bisa digunakan untuk mengakses halamannya. Untuk cara mengaksesnya, kamu bisa mengimplementasikan :
            - Dengan menambahkan elemen View khusus (bisa option menu, tombol, atau tab) yang mengandung ID “about_page”

**4. Fitur Pencarian**

     Syarat
      - Aplikasi memiliki fitur pencarian berdasarkan kata kunci yang dimasukkan, dengan ketentuan:
            - Jika kolom pencarian tidak kosong, maka aplikasi hanya menampilkan data yang judulnya mengandung kata kunci yang dimasukkan.
            - Jika kolom pencariannya kosong, maka aplikasi menampilkan seluruh data.
      - Memanfaatkan ViewModel dalam membangun fitur pencarian.
            
      
# Penilaian:
**1. Menerapkan tampilan aplikasi yang sesuai standar:**

      - Memiliki width, height, margin, dan padding yang sesuai.
      - Komponen tidak saling bertumpuk
      - Penggunaan komponen yang sesuai dengan fungsinya.
      - Penggunaan warna yang sesuai
      - Semua data dapat terlihat baik ketika landscape maupun potrait (gunakan ScrollView)

**2. Menuliskan kode dengan baik sesuai best-practice:**

       - Tidak membuat komponen yang tidak diperlukan.
       - Memecah UI menjadi komponen sekecil mungkin (sesuai tanggung jawabnya).
       - Menambahkan default Modifier pada setiap komponen.
       - Tidak menggunakan object sekaligus sebagai parameter, tetapi cukup yang dibutuhkan saja.
       - Menggunakan key untuk LazyList/LazyGrid.
       - Memanajemen state dengan tepat.

**3. Membuat fitur pencarian.**

**4. Membuat end-to-end testing untuk memeriksa fungsional seluruh halaman, baik positif case maupun negative case.**

**5. Membuat fitur menambahkan & menghapus data.**
