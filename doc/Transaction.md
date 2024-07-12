### TRANSACTIONAL

## Spring Data JPA

# Annotation Interface Transactional
### Package org.springframework.transaction.annotation

### Konsep dasar Transaction

ACID Properties:

- Atomicity: Seluruh operasi dalam transaksi dijalankan secara penuh atau tidak sama sekali. Jika salah satu operasi gagal, maka seluruh transaksi dibatalkan (rollback).
- Consistency: Transaksi harus membawa database dari satu keadaan yang konsisten ke keadaan konsisten lainnya.
- Isolation: Transaksi dijalankan secara independen, sehingga hasil dari transaksi yang sedang berlangsung tidak terlihat oleh transaksi lain sampai transaksi tersebut selesai.
- Durability: Hasil dari transaksi yang berhasil dijalankan harus disimpan secara permanen dalam basis data, bahkan jika terjadi kegagalan sistem setelah transaksi selesai.
  
Commit dan Rollback:

- Commit: Mengakhiri transaksi dengan menyimpan semua perubahan yang telah dibuat. Setelah commit, perubahan menjadi permanen.
- Rollback: Membatalkan transaksi dengan mengembalikan basis data ke keadaan sebelum transaksi dimulai.

Penggunaan Transaksi:

- Dalam SQL: Transaksi biasanya dimulai dengan perintah BEGIN TRANSACTION, diakhiri dengan COMMIT untuk menyimpan perubahan atau ROLLBACK untuk membatalkan perubahan.
- Dalam Pemrograman: Berbagai bahasa pemrograman dan framework menyediakan dukungan untuk transaksi. Misalnya, dalam Java dengan Spring Framework, transaksi dapat dikelola dengan anotasi @Transactional.
- Saat kita menggunakan JPA secara manual, kita harus melakukan management transaction secara manual menggunakan EntityManager
- Spring menyediakan fitur Declarative Transaction, yaitu management transaction secara declarative, yaitu dengan menggunakan annotation @Transactional
- Annotation ini secara otomatis dibaca oleh Spring AOP, dan akan menjalankan transaction secara otomatis ketika memanggil method yang terdapat annotation @Transactional nya

### https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/transaction/annotation/Transactional.html 

# Property dalam anotasi @Transaction

# Isolation (The transaction isolation level.)

## Skenario

Kita akan menggunakan dua transaksi (Transaction A dan Transaction B) untuk menguji perilaku berbagai level isolasi.
Pengaturan Awal:

Di dalam database, terdapat tabel Product dengan satu baris data:
- id: 1
- name: "Product A"
- quantity: 100

### Level Isolasi: READ_UNCOMMITTED

Deskripsi:

Level isolasi ini memungkinkan transaksi membaca data yang belum di-commit oleh transaksi lain. Hal ini dapat menyebabkan dirty reads.
Skenario:

- Transaction A memulai dan membaca quantity dari Product dengan id=1.
- Transaction B memulai dan mengubah quantity dari Product dengan id=1 menjadi 120 tetapi belum di-commit.
- Transaction A membaca kembali quantity dari Product dengan id=1 dan melihat nilai 120 (dirty read).
- Transaction B melakukan rollback sehingga perubahan tidak di-commit.

Hasil:

Transaction A melihat nilai quantity yang belum di-commit oleh Transaction B.

### Level Isolasi: READ_COMMITTED

Deskripsi:

Level isolasi ini mencegah dirty reads. Transaksi hanya dapat melihat data yang telah di-commit oleh transaksi lain.
Skenario:

- Transaction A memulai dan membaca quantity dari Product dengan id=1.
- Transaction B memulai dan mengubah quantity dari Product dengan id=1 menjadi 120 tetapi belum di-commit.
- Transaction A membaca kembali quantity dari Product dengan id=1 dan melihat nilai 100 (tidak melihat perubahan yang belum di-commit).
- Transaction B melakukan commit.
- Transaction A membaca kembali quantity dan melihat nilai 120.

Hasil:

Transaction A hanya melihat perubahan setelah Transaction B melakukan commit.

### Level Isolasi: REPEATABLE_READ

Deskripsi:

Level isolasi ini mencegah dirty reads dan non-repeatable reads. Transaksi akan melihat data yang konsisten pada setiap baca selama transaksi berlangsung.
Skenario:

- Transaction A memulai dan membaca quantity dari Product dengan id=1.
- Transaction B memulai dan mengubah quantity dari Product dengan id=1 menjadi 120 tetapi belum di-commit.
- Transaction A membaca kembali quantity dari Product dengan id=1 dan masih melihat nilai 100.
- Transaction B melakukan commit.
- Transaction A membaca kembali quantity dan tetap melihat nilai 100 hingga transaksi selesai.

Hasil:

- Transaction A melihat data yang sama di setiap baca meskipun 
- Transaction B telah melakukan commit.

### Level Isolasi: SERIALIZABLE

Deskripsi:

Level isolasi ini adalah yang tertinggi dan mencegah dirty reads, non-repeatable reads, dan phantom reads. Transaksi dieksekusi seolah-olah secara serial.
Skenario:

- Transaction A memulai dan membaca quantity dari Product dengan id=1.
- Transaction B memulai dan mencoba mengubah quantity dari Product dengan id=1 menjadi 120 tetapi harus menunggu hingga Transaction A selesai.
- Transaction A membaca kembali quantity dari Product dengan id=1 dan melihat nilai 100.
- Transaction A selesai dan melakukan commit.
- Transaction B sekarang dapat melanjutkan dan melakukan perubahan.

Hasil:

- Transaction B harus menunggu hingga Transaction A selesai sebelum melakukan perubahan.

Kesimpulan

- READ_UNCOMMITTED: Memungkinkan dirty reads, memberikan performa tertinggi tetapi konsistensi data terendah.
- READ_COMMITTED: Mencegah dirty reads, cukup sering digunakan karena kompromi antara konsistensi dan performa.
- REPEATABLE_READ: Mencegah dirty reads dan non-repeatable reads, memberikan konsistensi yang lebih tinggi dengan sedikit penurunan performa.
- SERIALIZABLE: Mencegah dirty reads, non-repeatable reads, dan phantom reads, memberikan konsistensi tertinggi dengan performa terendah.

# Label (Defines zero (0) or more transaction labels.)

Identifikasi Transaksi: Label digunakan untuk mengidentifikasi atau memberi nama pada transaksi tertentu. Ini memungkinkan Anda untuk membedakan satu transaksi dengan transaksi lainnya dalam kasus di mana Anda memiliki banyak transaksi dengan konfigurasi yang mirip.

Seleksi Transaksi: Dalam beberapa kasus, Anda mungkin memiliki beberapa definisi transaksi yang berbeda untuk berbagai keperluan dalam aplikasi Anda. Dengan menggunakan label, Anda dapat memilih transaksi yang tepat untuk digunakan dalam kasus tertentu.

Konfigurasi Lebih Lanjut: Label sering digunakan bersama dengan anotasi atau konfigurasi lainnya untuk transaksi, seperti timeout, jenis propagasi, tingkat isolasi, dan aturan rollback. Ini memungkinkan Anda untuk secara fleksibel mengatur perilaku transaksi yang berbeda berdasarkan kebutuhan bisnis Anda.


noRollbackFor (Defines zero (0) or more exception types, which must be subclasses of Throwable, indicating which exception types must not cause a transaction rollback.)

Definisi:
noRollbackFor mendefinisikan satu atau lebih jenis exception yang, ketika dilemparkan, tidak akan menyebabkan rollback transaksi. Exception ini harus merupakan subclass dari Throwable.
Tujuan:
Kadang-kadang Anda mungkin ingin menangani beberapa exception tanpa membatalkan semua perubahan dalam transaksi. noRollbackFor memungkinkan Anda untuk menentukan exception-exception yang tidak perlu menyebabkan rollback, sehingga beberapa perubahan dapat tetap dipertahankan meskipun terjadi exception tertentu.
Penggunaan:
noRollbackFor biasanya digunakan ketika Anda ingin melanjutkan transaksi atau menangani exception secara khusus tanpa kehilangan semua pekerjaan yang sudah dilakukan dalam transaksi tersebut.

